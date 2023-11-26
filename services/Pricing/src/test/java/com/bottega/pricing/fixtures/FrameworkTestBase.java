package com.bottega.pricing.fixtures;

import lombok.extern.slf4j.Slf4j;
import org.apache.groovy.util.Maps;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.*;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import static com.bottega.sharedlib.config.CdcStubs.CDC_STUB_ID_VENDOR;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;
import static org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode.LOCAL;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

@SpringBootTest(webEnvironment = DEFINED_PORT)
@ActiveProfiles({"test"})
@AutoConfigureStubRunner(ids = CDC_STUB_ID_VENDOR, stubsMode = LOCAL)
@Testcontainers
//@AutoConfigureMessageVerifier
@Slf4j
public class FrameworkTestBase {

    static KafkaContainer kafka;
    static AdminClient kafkaAdmin;

    static {
        //singleton container pattern: https://java.testcontainers.org/test_framework_integration/manual_lifecycle_control/#singleton-containers
        kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.4.3"));
        kafka.start();
    }

    @Autowired
    protected SharedFixtures sharedFixtures;
    @Autowired
    protected PriceFixtures priceFixtures;
    @Autowired
    protected InitPriceFixtures initPriceFixtures;
    @Autowired
    protected KafkaContainerFixtures kafkaContainerFixtures;
    @Autowired
    protected TestBuilders builders;


    @DynamicPropertySource
    static void kafkaProperties(DynamicPropertyRegistry registry) {
        await().until(kafka::isRunning);
        registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);

        kafkaAdmin = AdminClient.create(Maps.of(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafka.getBootstrapServers()));

        log.info("kafka configured at " + kafka.getBootstrapServers());
    }

    @BeforeEach
    void beforeEach() {
        kafkaContainerFixtures.beforeEach();
    }

    @AfterEach
    void tearDown() {
        priceFixtures.tearDown();
        sharedFixtures.tearDown();
    }
//    @Autowired
//    private KafkaMessageVerifier kafkaMessageVerifier;

//    void beforeEach() {
//        kafkaMessageVerifier.reset();
//    }
}
//@Configuration
//@Slf4j
//class TestConfig {
//
//    @Bean
//    MessageVerifierSender<Message<?>> standaloneMessageVerifier(KafkaTemplate kafkaTemplate) {
//        return new MessageVerifierSender<>() {
//
//            @Override
//            public void send(Message<?> message, String destination, @Nullable YamlContract contract) {
//            }
//
//            @Override
//            public <T> void send(T payload, Map<String, Object> headers, String destination, @Nullable YamlContract contract) {
//                Map<String, Object> newHeaders = headers != null ? new HashMap<>(headers) : new HashMap<>();
//                newHeaders.put(KafkaHeaders.TOPIC, destination);
//                log.info("Sending: " + payload + destination);
//                kafkaTemplate.send(MessageBuilder.createMessage(payload, new MessageHeaders(newHeaders)));
//            }
//        };
//    }
//
//    @Bean
//    @Primary
//    JsonMessageConverter noopMessageConverter() {
//        return new NoopJsonMessageConverter();
//    }
//
//    @Bean
//    KafkaMessageVerifier kafkaTemplateMessageVerifier() {
//        return new KafkaMessageVerifier();
//    }
//}
//
//
//class KafkaMessageVerifier implements MessageVerifierReceiver<Message<?>> {
//
//    private static final Log LOG = LogFactory.getLog(KafkaMessageVerifier.class);
//
//    Map<String, BlockingQueue<Message<?>>> broker = new ConcurrentHashMap<>();
//
//
//    @Override
//    public Message receive(String destination, long timeout, TimeUnit timeUnit, @Nullable YamlContract contract) {
//        broker.putIfAbsent(destination, new ArrayBlockingQueue<>(1));
//        BlockingQueue<Message<?>> messageQueue = broker.get(destination);
//        Message<?> message;
//        try {
//            message = messageQueue.poll(timeout, timeUnit);
//        }
//        catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        if (message != null) {
//            LOG.info("Removed a message from a topic [" + destination + "]");
//        }
//        return message;
//    }
//
//
//    @KafkaListener(id = "baristaContractTestListener", topicPattern = ".*")
//    public void listen(ConsumerRecord payload, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
//        LOG.info("Got a message from a topic [" + topic + "]");
//        Map<String, Object> headers = new HashMap<>();
//        new DefaultKafkaHeaderMapper().toHeaders(payload.headers(), headers);
//        broker.putIfAbsent(topic, new ArrayBlockingQueue<>(1));
//        BlockingQueue<Message<?>> messageQueue = broker.get(topic);
//        messageQueue.add(MessageBuilder.createMessage(payload.value(), new MessageHeaders(headers)));
//    }
//
//    @Override
//    public Message receive(String destination, YamlContract contract) {
//        return receive(destination, 15, TimeUnit.SECONDS, contract);
//    }
//
//}

//class NoopJsonMessageConverter extends JsonMessageConverter {
//
//    NoopJsonMessageConverter() {
//    }
//
//    @Override
//    protected Object convertPayload(Message<?> message) {
//        return message.getPayload();
//    }
//}