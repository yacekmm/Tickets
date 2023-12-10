package com.bottega.promoter.concert.api.rest;

import java.util.Map;

import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.*;
import au.com.dius.pact.core.model.*;
import au.com.dius.pact.core.model.annotations.Pact;
import com.bottega.promoter.concert.domain.Concert;
import com.bottega.promoter.fixtures.FrameworkTestBase;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.*;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "Tickets.Pricing", port = "8181", pactVersion = PactSpecVersion.V3)
public class ConcertRestController_discountConcert_restApiTest extends FrameworkTestBase {

    @Pact(provider = "Tickets.Pricing", consumer = "Tickets.Promoter")
    public RequestResponsePact createPact(PactDslWithProvider builder) {

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

    @Test
    public void discountConcert_discounts_onValidRequest() {
        Concert concert = builders.aConcert().withId("123").inDb();

        //when
        ValidatableResponse response = concertFixtures.concertHttpClient.discountConcert(concert.getId(), 10);

        //then
        response
                .statusCode(SC_OK)
                .body("$", hasSize(1))
                .body("[0].price", equalTo(90_00));
    }

}
