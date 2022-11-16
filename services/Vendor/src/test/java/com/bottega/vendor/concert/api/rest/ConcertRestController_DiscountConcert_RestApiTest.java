package com.bottega.vendor.concert.api.rest;

import com.bottega.vendor.concert.domain.Concert;
import com.bottega.vendor.fixtures.FrameworkTestBase;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.*;

public class ConcertRestController_DiscountConcert_RestApiTest extends FrameworkTestBase {


    @Test
    public void discountConcert_discounts_onValidRequest() {
        Concert concert = builders.aConcert().inDb();

        //when
        ValidatableResponse response = concertFixtures.concertHttpClient.discountConcert(concert.getId(), 10);

        //then
        response
                .statusCode(SC_OK)
                .body("$", hasSize(1))
                .body("[0].price", equalTo(90_00));
    }

}
