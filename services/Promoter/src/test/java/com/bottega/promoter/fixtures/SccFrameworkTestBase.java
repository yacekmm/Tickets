package com.bottega.promoter.fixtures;

import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerExtension;
import org.springframework.web.reactive.function.client.WebClient;
import static com.bottega.sharedlib.config.CdcStubs.CDC_STUB_ID_PRICING;
import static org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode.LOCAL;

public class SccFrameworkTestBase extends FrameworkTestBase {

    @RegisterExtension
    static StubRunnerExtension stubRunnerExtension = new StubRunnerExtension()
            .stubsMode(LOCAL)
            .downloadStub(CDC_STUB_ID_PRICING);

}
