package com.bottega.vendor.concertRead;

import com.bottega.vendor.fixtures.FrameworkTestBase;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.*;

public class ConcertReadRestController_GetConcerts_RestApiTest extends FrameworkTestBase {


    @Test
    public void findConcerts_returnsSortedConcertsForVendor_onValidRequest() {
        //given

        //when
        ValidatableResponse response = concertFixtures.concertHttpClient.findConcertsForVendor("vendor");

        //then
        response
                .statusCode(SC_OK)
                .body("$", hasSize(2))
                .body("[0].id", equalTo(concert_1.getId().asString()))
                .body("[1].id", equalTo(concert_2.getId().asString()));
    }

}
