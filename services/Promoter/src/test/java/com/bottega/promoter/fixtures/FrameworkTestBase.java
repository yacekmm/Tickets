package com.bottega.promoter.fixtures;

import com.bottega.promoter.concert.domain.ConcertFixtures;
import lombok.extern.slf4j.Slf4j;
import org.apache.groovy.util.Maps;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.kafka.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

@SpringBootTest(webEnvironment = DEFINED_PORT)
@ActiveProfiles({"test"})
@Testcontainers
@Slf4j
public class FrameworkTestBase {

    static KafkaContainer kafka;
    static AdminClient kafkaAdmin;

    static {
        //singleton container pattern: https://java.testcontainers.org/test_framework_integration/manual_lifecycle_control/#singleton-containers
        kafka = new KafkaContainer(DockerImageName.parse("apache/kafka:3.9.0"))
                .withEnv("KAFKA_LISTENERS", "PLAINTEXT://:9092,BROKER://:9093,CONTROLLER://:9094");
        kafka.start();
    }

    @Autowired
    public TestBuilders builders;
    @Autowired
    protected ConcertFixtures concertFixtures;
    @Autowired
    protected ConcertReadFixtures concertReadFixtures;
    @Autowired
    protected SharedFixtures sharedFixtures;
    @Autowired
    protected KafkaContainerFixtures kafkaContainerFixtures;

    @DynamicPropertySource
    static void kafkaProperties(DynamicPropertyRegistry registry) {
        await().until(kafka::isRunning);
        registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);

        kafkaAdmin = AdminClient.create(Maps.of(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafka.getBootstrapServers()));

        log.info("kafka configured at " + kafka.getBootstrapServers());
    }

    @BeforeEach
    public void beforeEach() {
        kafkaContainerFixtures.beforeEach();
    }


    @AfterEach
    void tearDown() {
        concertFixtures.tearDown();
        concertReadFixtures.tearDown();
        sharedFixtures.tearDown();
    }
}