package com.bottega.promoter.infra.client.pricing;

import com.bottega.promoter.concert.domain.ConcertId;
import com.bottega.promoter.concert.fixtures.PricingStubs;
import com.bottega.promoter.fixtures.FrameworkTestBase;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.bottega.promoter.concert.fixtures.asserts.PriceAssert.assertThatPrice;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class PricingClient_applyDiscount_liveCoding_wiremockDepTest extends FrameworkTestBase {

    PricingClient httpPricingClient;

    @Autowired
    PricingStubs pricingStubs;

    static WireMockServer pricingMockServer;

    @BeforeEach
    void setUp() {
        httpPricingClient = concertFixtures.pricingClient;

        pricingMockServer = new WireMockServer(options().port(8181)
                .notifier(new ConsoleNotifier(true)));
        pricingMockServer.start();
    }

    @AfterEach
    void tearDown() {
        pricingMockServer.stop();
    }

    @Test
    public void applyDiscount_isValid() {
        //given
        pricingStubs.stubApplyPercentageDiscount(pricingMockServer);

        //when
        var result = httpPricingClient.applyPercentageDiscount(new ConcertId("123"), 10);

        //then
        assertThatPrice(result.get().getFirst())
                .equalTo(90_00);
    }

}
