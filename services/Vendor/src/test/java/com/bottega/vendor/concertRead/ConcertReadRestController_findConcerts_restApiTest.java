package com.bottega.vendor.concertRead;

import com.bottega.vendor.concert.domain.Concert;
import com.bottega.vendor.fixtures.FrameworkTestBase;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;
import static com.bottega.sharedlib.config.TestClockConfig.*;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.*;

public class ConcertReadRestController_findConcerts_restApiTest extends FrameworkTestBase {

    @Test
    public void findConcerts_returnsSortedConcertsForVendor_onValidRequest() {
        //given
        Concert concert_1 = builders.aConcert().withDate(TEST_TIME_PLUS_30_DAYS).withVendorId("vendor").inDb();
        Concert concert_2 = builders.aConcert().withDate(TEST_TIME_PLUS_60_DAYS).withVendorId("vendor").inDb();
        builders.aConcert().withVendorId("other").inDb();

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
