package com.bottega.promoter.infra.client.pricing;

import com.bottega.promoter.concert.domain.ConcertId;
import com.bottega.promoter.concert.fixtures.PricingStubs;
import com.bottega.promoter.fixtures.FrameworkTestBase;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureWireMock(port = 8181)
public class PricingClient_applyDiscount_liveCoding_wiremockDepTest extends FrameworkTestBase {

    PricingClient httpPricingClient;

    @Autowired
    PricingStubs pricingStubs;

    @BeforeEach
    void setUp() {
        httpPricingClient = concertFixtures.pricingClient;
        WireMock.reset();
    }

    @Test
    public void applyDiscount_isValid() {
        //given
        pricingStubs.stubApplyPercentageDiscount();

        //when
        var result = httpPricingClient.applyPercentageDiscount(new ConcertId("123"), 10);

        //then
        assertThat(result.get().getFirst().getPrice().toInt()).isEqualTo(90_00);
    }

}
