package com.bottega.promoter.concert.api.rest;

import com.bottega.promoter.concert.domain.Concert;
import com.bottega.promoter.fixtures.PactFrameworkTestBase;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;


import static com.bottega.promoter.concert.fixtures.PriceJsonAssert.assertThatPrice;

public class ConcertRestController_discountConcert_restApiTest extends PactFrameworkTestBase {

    @Test
    public void discountConcert_discounts_onValidRequest() {
        //given
        Concert concert = builders.aConcert().withId("123").inDb();

        //when
        ValidatableResponse response = concertFixtures.concertHttpClient.discountConcert(concert.getId(), 10);

        //then
        assertThatPrice(response)
                .hasSinglePrice(90_00);
    }

}
