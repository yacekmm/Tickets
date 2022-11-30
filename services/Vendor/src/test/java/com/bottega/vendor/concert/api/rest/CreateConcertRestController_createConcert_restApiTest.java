package com.bottega.vendor.concert.api.rest;

import com.bottega.sharedlib.fixtures.*;
import com.bottega.vendor.concert.domain.ConcertId;
import com.bottega.vendor.concert.fixtures.asserts.ConcertAssert;
import com.bottega.vendor.concert.fixtures.clients.ConcertHttpClient.ConcertRequest;
import com.bottega.vendor.fixtures.FrameworkTestBase;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;
import static com.bottega.sharedlib.config.TestClockConfig.*;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;

public class CreateConcertRestController_createConcert_restApiTest extends FrameworkTestBase {


    @Test
    public void createConcert_OK_onValidRequest() {
        //given
        ConcertRequest concertRequest = ConcertRequest.builder().build();

        //when
        ValidatableResponse response = concertFixtures.concertHttpClient.createConcert(concertRequest);

        //then
        response
                .statusCode(SC_OK);

        ConcertId concertId = ConcertAssert
                .assertThatConcert(concertFixtures.concertRepo.findAll().iterator().next())
                .isPersistedIn(concertFixtures.concertRepo, RepoEntries.SINGULAR)
                .hasTitle("default-mock-title")
                .hasIdAsUUID()
                .hasDate(TEST_TIME_PLUS_30_DAYS)
                .hasVendorId("default-vendor-id")
                .extractId();

        response
                .body("id", equalTo(concertId.asString()));
    }


    @Test
    public void createConcert_returnsBadRequest_onDateTooSoon() {
        //given
        ConcertRequest concertRequest = ConcertRequest.builder().date(TEST_TIME).build();

        //when
        ValidatableResponse response = concertFixtures.concertHttpClient.createConcert(concertRequest);

        //then
        ErrorJsonAssert.assertThatError(response)
                .isBadRequest("invalid_date");
    }



}
