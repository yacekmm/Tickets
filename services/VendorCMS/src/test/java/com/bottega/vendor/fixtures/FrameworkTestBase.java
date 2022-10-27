package com.bottega.vendor.fixtures;

import com.bottega.vendor.concert.fixtures.fixtures.FrameworkConcertFixtures;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.ActiveProfiles;

import static com.bottega.vendor.config.CdcStubs.CDC_STUB_ID_PRICING;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;
import static org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode.LOCAL;

@SpringBootTest(webEnvironment = DEFINED_PORT)
@ActiveProfiles({"test"})
@AutoConfigureStubRunner(
        ids = CDC_STUB_ID_PRICING,
        stubsMode = LOCAL)
public class FrameworkTestBase {

    @Autowired
    protected FrameworkConcertFixtures concertFixtures;

    @AfterEach
    void tearDown() {
        concertFixtures.tearDown();
    }
}
