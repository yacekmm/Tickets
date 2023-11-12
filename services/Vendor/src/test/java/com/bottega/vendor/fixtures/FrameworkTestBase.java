package com.bottega.vendor.fixtures;

import javax.annotation.Nullable;
import java.util.*;
import java.util.concurrent.*;

import com.bottega.vendor.concert.domain.ConcertFixtures;
import org.apache.commons.logging.*;
import org.apache.groovy.util.Maps;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.verifier.converter.YamlContract;
import org.springframework.cloud.contract.verifier.messaging.MessageVerifierReceiver;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.context.annotation.*;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.*;
import org.springframework.messaging.*;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.*;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import static com.bottega.sharedlib.config.CdcStubs.CDC_STUB_ID_PRICING;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;
import static org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode.LOCAL;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

@SpringBootTest(webEnvironment = DEFINED_PORT)
@ActiveProfiles({"test"})
@AutoConfigureStubRunner(ids = CDC_STUB_ID_PRICING, stubsMode = LOCAL)
@Testcontainers
@AutoConfigureMessageVerifier
public class FrameworkTestBase {

    //singleton container pattern: https://java.testcontainers.org/test_framework_integration/manual_lifecycle_control/#singleton-containers
    static final KafkaContainer kafka;
    static AdminClient kafkaAdmin;

    static {
        kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.4.3"));
        kafka.start();
    }

    @DynamicPropertySource
    static void kafkaProperties(DynamicPropertyRegistry registry) {
        await().until(kafka::isRunning);
        registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);

        kafkaAdmin = AdminClient.create(Maps.of(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafka.getBootstrapServers()));
    }


    @Autowired
    protected ConcertFixtures concertFixtures;

    @Autowired
    protected ConcertReadFixtures concertReadFixtures;

    @Autowired
    protected SharedFixtures sharedFixtures;

    @Autowired
    public TestBuilders builders;

    @Autowired
    private KafkaMessageVerifier kafkaMessageVerifier;

    @BeforeEach
    void beforeEach() {
        kafkaMessageVerifier.reset();
    }

    @AfterEach
    void tearDown() {
        concertFixtures.tearDown();
        concertReadFixtures.tearDown();
        sharedFixtures.tearDown();
    }
}

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

    public void reset() {
        broker.clear();
    }

    @Override
    public Message receive(String destination, long timeout, TimeUnit timeUnit, @Nullable YamlContract contract) {
        broker.putIfAbsent(destination, new ArrayBlockingQueue<>(1));
        Message<?> message;
        try {
            message = broker.get(destination).poll(timeout, timeUnit);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (message != null) {
            LOG.info("Removed a message from a topic [" + destination + "]: " + message);
        }
        return message;
    }


    @KafkaListener(id = "contractTestListener", topicPattern = ".*")
    public void listen(ConsumerRecord payload, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        LOG.info("Got a message from a topic [" + topic + "]: " + payload);
        Map<String, Object> headers = new HashMap<>();
        new DefaultKafkaHeaderMapper().toHeaders(payload.headers(), headers);
        broker.putIfAbsent(topic, new ArrayBlockingQueue<>(1));
        broker.get(topic).add(MessageBuilder.createMessage(payload.value(), new MessageHeaders(headers)));
    }

    @Override
    public Message receive(String destination, YamlContract contract) {
        return receive(destination, 15, TimeUnit.SECONDS, contract);
    }

}