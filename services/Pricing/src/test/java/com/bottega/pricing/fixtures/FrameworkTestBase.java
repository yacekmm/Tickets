package com.bottega.pricing.fixtures;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;

import static com.bottega.sharedlib.config.CdcStubs.CDC_STUB_ID_VENDOR;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;
import static org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode.LOCAL;

@SpringBootTest(webEnvironment = DEFINED_PORT)
@ActiveProfiles({"test"})
@AutoConfigureStubRunner(ids = CDC_STUB_ID_VENDOR, stubsMode = LOCAL)
@EmbeddedKafka(partitions = 1, topics = { "pricing.price" }, brokerProperties = { "listeners=PLAINTEXT://localhost:19091", "port=19091" })
public class FrameworkTestBase {

    @Autowired
    protected SharedFixtures sharedFixtures;
    @Autowired
    protected PriceFixtures priceFixtures;
    @Autowired
    protected InitPriceFixtures initPriceFixtures;
    @Autowired
    protected TestBuilders builders;


    @AfterEach
    void tearDown() {
        priceFixtures.tearDown();
        sharedFixtures.tearDown();
    }

}
