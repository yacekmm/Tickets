package com.bottega.vendor.fixtures;

import javax.annotation.Nullable;
import java.util.*;
import java.util.concurrent.*;

import com.bottega.vendor.concert.domain.ConcertFixtures;
import org.apache.commons.logging.*;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.verifier.converter.YamlContract;
import org.springframework.cloud.contract.verifier.messaging.MessageVerifierReceiver;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.context.annotation.*;
import org.springframework.kafka.annotation.*;
import org.springframework.kafka.support.*;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.messaging.*;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.*;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.*;
import org.testcontainers.shaded.org.awaitility.Awaitility;
import org.testcontainers.utility.DockerImageName;

import static com.bottega.sharedlib.config.CdcStubs.CDC_STUB_ID_PRICING;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;
import static org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode.LOCAL;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

@SpringBootTest(webEnvironment = DEFINED_PORT)
@ActiveProfiles({"test"})
@AutoConfigureStubRunner(ids = CDC_STUB_ID_PRICING, stubsMode = LOCAL)
//@EmbeddedKafka(partitions = 1, topics = { "vendor.concert" }, brokerProperties = { "listeners=PLAINTEXT://localhost:19092", "port=19092" })
@Testcontainers
@AutoConfigureMessageVerifier
public class FrameworkTestBase {

    @Container
    static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:6.2.1")).withEmbeddedZookeeper();

    @DynamicPropertySource
    static void kafkaProperties(DynamicPropertyRegistry registry) {
        kafka.start();
        await().until(() -> kafka.isRunning());
//        System.out.println("--------------------------- " + kafka.getBootstrapServers());
        registry.add("spring.kafka.bootstrap-servers", () -> {
            return List.of(kafka.getHost() + ":" + kafka.getFirstMappedPort());
        });
//        registry.add("spring.kafka.bootstrap-servers", () -> List.of(kafka.getBootstrapServers()));
    }


    @Autowired
    protected ConcertFixtures concertFixtures;

    @Autowired
    protected ConcertReadFixtures concertReadFixtures;

    @Autowired
    protected
    SharedFixtures sharedFixtures;

    @Autowired
    public TestBuilders builders;

    @AfterEach
    void tearDown() {
        concertFixtures.tearDown();
        concertReadFixtures.tearDown();
        sharedFixtures.tearDown();
    }
}

//@EnableKafka
@Configuration
class TestConfig {

    @Bean
    KafkaMessageVerifier kafkaTemplateMessageVerifier() {
        return new KafkaMessageVerifier();
    }

}


class KafkaMessageVerifier implements MessageVerifierReceiver<Message<?>> {

    private static final Log LOG = LogFactory.getLog(KafkaMessageVerifier.class);

    Map<String, BlockingQueue<Message<?>>> broker = new ConcurrentHashMap<>();


    @Override
    public Message receive(String destination, long timeout, TimeUnit timeUnit, @Nullable YamlContract contract) {
        broker.putIfAbsent(destination, new ArrayBlockingQueue<>(1));
        BlockingQueue<Message<?>> messageQueue = broker.get(destination);
        Message<?> message;
        try {
            message = messageQueue.poll(timeout, timeUnit);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (message != null) {
            LOG.info("Removed a message from a topic [" + destination + "]");
        }
        return message;
    }


    @KafkaListener(id = "baristaContractTestListener", topicPattern = ".*")
    public void listen(ConsumerRecord payload, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        LOG.info("Got a message from a topic [" + topic + "]");
        Map<String, Object> headers = new HashMap<>();
        new DefaultKafkaHeaderMapper().toHeaders(payload.headers(), headers);
        broker.putIfAbsent(topic, new ArrayBlockingQueue<>(1));
        BlockingQueue<Message<?>> messageQueue = broker.get(topic);
        messageQueue.add(MessageBuilder.createMessage(payload.value(), new MessageHeaders(headers)));
    }

    @Override
    public Message receive(String destination, YamlContract contract) {
        return receive(destination, 15, TimeUnit.SECONDS, contract);
    }

}