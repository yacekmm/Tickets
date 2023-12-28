package com.bottega.promoter.concert.fixtures;

import java.util.Map;

import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.core.model.RequestResponsePact;
import org.springframework.stereotype.Component;

@Component
public class PricingPactFixtures {

    public RequestResponsePact applyPercentageDiscount(PactDslWithProvider builder) {

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
