package com.bottega.vendor.concert.api;

import com.bottega.vendor.concert.domain.ConcertId;
import com.bottega.vendor.concert.tests.asserts.ConcertAssert;
import com.bottega.vendor.tests.FrameworkTestBase;
import com.bottega.vendor.tests.RepoEntries;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import static com.bottega.vendor.tests.config.TestClockConfig.TEST_TIME_PLUS_30_DAYS;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;

public class CreateConcert_RestApiTest extends FrameworkTestBase {


    @Test
    public void createConcert_creates_onValidRequest() {

        //when
        ValidatableResponse response = concertFixtures.concertClient.createConcert("concert-title", TEST_TIME_PLUS_30_DAYS.toString(), "some-id");

        //then
        ConcertId concertId = ConcertAssert
                .assertThatConcert(concertFixtures.concertRepo.findAll().iterator().next())
//        //TODO: concert Properties in DB: Dependency test
                .isPersistedIn(concertFixtures.concertRepo, RepoEntries.SINGULAR)
                .hasIdAsUUID()
                .hasTitle("concert-title")
                .hasDateTime(TEST_TIME_PLUS_30_DAYS)
                .hasVendorId("some-id")
                .extractId();


        //TODO: API response is valid: API test
        response
                .statusCode(SC_OK)
                .body("id", equalTo(concertId.asString()));
    }

}
