package com.bottega.pricing.fixtures;

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

}
