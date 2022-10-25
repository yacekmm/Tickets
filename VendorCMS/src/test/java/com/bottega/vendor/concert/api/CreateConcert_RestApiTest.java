package com.bottega.vendor.concert.api;

import com.bottega.vendor.concert.domain.ConcertId;
import com.bottega.vendor.concert.tests.asserts.ConcertAssert;
import com.bottega.vendor.concert.tests.fixtures.FrameworkConcertFixtures;
import com.bottega.vendor.tests.FrameworkTestBase;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.bottega.vendor.tests.config.TestClockConfig.TEST_TIME_PLUS_30_DAYS;
import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;

public class CreateConcert_RestApiTest extends FrameworkTestBase {

    @Autowired
    FrameworkConcertFixtures concertFixtures;

    @Test
    public void createConcert_creates_onValidRequest() {
        //given
        String title = "concert-title";
        String dateTime = TEST_TIME_PLUS_30_DAYS.toString();
        String vendorId = "some-id";

        //when
        Response response = concertFixtures.concertClient.createConcert(title, dateTime, vendorId);

        //then
        ConcertId concertId = ConcertAssert
                .assertThatConcert(concertFixtures.concertRepo.findAll().iterator().next())
                .isPersistedIn(concertFixtures.concertRepo, 1)
                .hasIdAsUUID()
                .hasTitle(title)
                .hasDateTime(TEST_TIME_PLUS_30_DAYS)
                .hasVendorId(vendorId)
                .extractId();


        //API response is valid
        assertThat(response.statusCode()).isEqualTo(SC_OK);
        assertThat(response.jsonPath().getString("id")).isEqualTo(concertId.asString());
    }

}
