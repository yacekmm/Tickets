package com.bottega.promoter.infra.client.pricing;

import java.util.List;

import com.bottega.promoter.concert.Price;
import com.bottega.promoter.concert.domain.ConcertId;
import com.bottega.promoter.fixtures.*;
import com.bottega.promoter.infra.client.WebClientsConfig;
import com.bottega.sharedlib.config.ServicesProperties;
import com.bottega.sharedlib.vo.error.ErrorResult;
import io.vavr.control.Either;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import static com.bottega.sharedlib.config.CdcStubs.SCC_PRICING_PORT;
import static org.assertj.core.api.Assertions.assertThat;

public class PricingClient_applyDiscount_sccDepTest extends SccFrameworkTestBase {

    FakePricingClient fakePricingClient;
    PricingClient realPricingClient;
    @Autowired
    ServicesProperties servicesProperties;

    @BeforeEach
    public void setUp() {
        fakePricingClient = new FakePricingClient();
        WebClient sccPricingWebClient = WebClientsConfig.buildPricingWebClient(servicesProperties.getPricing().host(), SCC_PRICING_PORT);
        realPricingClient = new HttpPricingClient(sccPricingWebClient);
    }

    @Test
    public void fakeApplyDiscount_isValid() {
        //given
        ConcertId concertId = new ConcertId("00000000-0000-0000-0000-000000000000");

        //when
        Either<ErrorResult, List<Price>> realResult = realPricingClient.applyPercentageDiscount(concertId, 10);
        Either<ErrorResult, List<Price>> fakeResult = fakePricingClient.applyPercentageDiscount(concertId, 10);

        //then
        assertThat(fakeResult.get()).containsExactlyInAnyOrderElementsOf(realResult.get());
    }

}
