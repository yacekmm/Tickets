package com.bottega.vendor.fixtures;

import com.bottega.vendor.concert.domain.ConcertFixtures;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;

import static com.bottega.sharedlib.config.CdcStubs.CDC_STUB_ID_PRICING;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;
import static org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode.LOCAL;

@SpringBootTest(webEnvironment = DEFINED_PORT)
@ActiveProfiles({"test"})
@AutoConfigureStubRunner(ids = CDC_STUB_ID_PRICING, stubsMode = LOCAL)
@EmbeddedKafka(partitions = 1, topics = { "vendor.concert" }, brokerProperties = { "listeners=PLAINTEXT://localhost:19092", "port=19092" })
public class FrameworkTestBase {

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
