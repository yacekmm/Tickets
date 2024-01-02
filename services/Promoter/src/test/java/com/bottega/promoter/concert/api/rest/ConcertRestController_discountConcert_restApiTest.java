package com.bottega.promoter.concert.api.rest;

import au.com.dius.pact.consumer.junit5.*;
import com.bottega.promoter.concert.domain.Concert;
import com.bottega.promoter.fixtures.*;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.*;

public class ConcertRestController_discountConcert_restApiTest extends PactFrameworkTestBase {

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
