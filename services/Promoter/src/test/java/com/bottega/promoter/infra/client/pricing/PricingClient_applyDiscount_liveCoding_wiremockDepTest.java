package com.bottega.promoter.infra.client.pricing;

import com.bottega.promoter.concert.domain.ConcertId;
import com.bottega.promoter.fixtures.FrameworkTestBase;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureWireMock(port = 8181)
public class PricingClient_applyDiscount_liveCoding_wiremockDepTest extends FrameworkTestBase {

    PricingClient httpPricingClient;

    @BeforeEach
    void setUp() {
        httpPricingClient = concertFixtures.pricingClient;
        WireMock.reset();
    }

    @Test
    public void applyDiscount_isValid() {
        //given
        WireMock.stubFor(WireMock.post(WireMock.urlEqualTo("/api/v1/item/123/price-factor/percentage"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                        [
                          {
                            "price": 9000,
                            "factors": [
                              {
                                "type": "PERCENTAGE",
                                "value": 10
                              }
                            ]
                          }
                        ]
                        """)));

        //when
        var result = httpPricingClient.applyPercentageDiscount(new ConcertId("123"), 10);

        //then
        assertThat(result.get().getFirst().getPrice().toInt()).isEqualTo(90_00);
    }

}
