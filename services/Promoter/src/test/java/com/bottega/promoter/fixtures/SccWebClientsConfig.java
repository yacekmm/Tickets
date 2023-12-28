package com.bottega.promoter.fixtures;

import com.bottega.promoter.infra.client.WebClientsConfig;
import com.bottega.sharedlib.config.ServicesProperties;
import org.springframework.context.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import static com.bottega.sharedlib.config.CdcStubs.SCC_PRICING_PORT;

@Configuration
public class SccWebClientsConfig {

    @Bean
    public WebClient sccPricingWebClient(ServicesProperties servicesProperties) {
        return WebClientsConfig.buildPricingWebClient(servicesProperties.getPricing().host(), SCC_PRICING_PORT);
    }
}
