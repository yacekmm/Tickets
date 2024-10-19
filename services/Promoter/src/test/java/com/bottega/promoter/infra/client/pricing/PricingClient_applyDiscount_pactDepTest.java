package com.bottega.promoter.infra.client.pricing;

import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.PactSpecVersion;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.bottega.promoter.concert.Price;
import com.bottega.promoter.concert.domain.ConcertId;
import com.bottega.promoter.fixtures.FrameworkTestBase;
import com.bottega.sharedlib.vo.Money;
import com.bottega.sharedlib.vo.error.ErrorResult;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Map;

import static com.bottega.promoter.concert.fixtures.asserts.PriceAssert.assertThatPrice;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "Tickets.Pricing", port = "8181", pactVersion = PactSpecVersion.V3)
public class PricingClient_applyDiscount_pactDepTest extends FrameworkTestBase {


    @Test
    public void applyDiscount_isValid() {
        //given
        ConcertId concertId = new ConcertId("123");

        //when
        Either<ErrorResult, List<Price>> result = concertFixtures.pricingClient.applyPercentageDiscount(concertId, 10);

        //then
        assertThatPrice(result.get().get(0))
                .equalTo(new Money(90_00));
    }

    @Pact(consumer = "Tickets.Promoter")
    public RequestResponsePact stubPricing_applyDiscount(PactDslWithProvider builder) {
        return builder
                .given("price for item exists", "itemId", "123")
                .uponReceiving("a discount request")
                .path("/api/v1/item/123/price-factor/percentage")
                .method("POST")
                .headers(Map.of("Content-Type", "application/json"))
                .body("""
                        {
                          "percentage": 10
                        }
                        """)
                .willRespondWith()
                .status(200)
                .headers(Map.of("Content-Type", "application/json"))
                .body("""
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
                        """)
                .toPact();
    }

}
