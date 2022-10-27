package com.bottega.pricing.fixtures;

import com.bottega.pricing.price.fixtures.FrameworkPriceFixtures;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;

import static com.bottega.sharedlib.config.CdcStubs.CDC_STUB_ID_VENDOR;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;
import static org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode.LOCAL;

@SpringBootTest(webEnvironment = DEFINED_PORT)
@ActiveProfiles({"test"})
@AutoConfigureStubRunner(
        ids = CDC_STUB_ID_VENDOR,
        stubsMode = LOCAL)
@EmbeddedKafka(partitions = 1, topics = { "pricing.price" }, brokerProperties = { "listeners=PLAINTEXT://localhost:19091", "port=19091" })
@AutoConfigureMessageVerifier
public class FrameworkTestBase {

    @Autowired
    protected FrameworkPriceFixtures priceFixtures;
    @Autowired
    protected SharedFixtures sharedFixtures;


    @AfterEach
    void tearDown() {
        priceFixtures.tearDown();
        sharedFixtures.tearDown();
    }

}
