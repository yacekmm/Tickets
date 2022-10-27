package com.bottega.pricing.fixtures;

import com.bottega.pricing.price.fixtures.FrameworkPriceFixtures;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@SpringBootTest(webEnvironment = DEFINED_PORT)
@ActiveProfiles({"test"})
@EmbeddedKafka(partitions = 1, topics = { "pricing.priceChange" }, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
@AutoConfigureMessageVerifier
public class FrameworkTestBase {

    @Autowired
    protected FrameworkPriceFixtures factorFixtures;

    @Autowired
    protected FrameworkPriceFixtures priceFixtures;


    @AfterEach
    void tearDown() {
        factorFixtures.tearDown();
    }

}
