package com.bottega.vendor.concert.api.rest;

import com.bottega.sharedlib.fixtures.*;
import com.bottega.vendor.concert.domain.ConcertId;
import com.bottega.vendor.concert.fixtures.asserts.ConcertAssert;
import com.bottega.vendor.fixtures.FrameworkTestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.apache.groovy.util.Maps;
import org.junit.jupiter.api.Test;

import static com.bottega.sharedlib.config.TestClockConfig.*;
import static io.restassured.http.ContentType.JSON;
import static java.time.LocalDate.ofInstant;
import static java.time.ZoneOffset.UTC;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;

public class CreateConcertRestController_CreateConcert_RestApiTest extends FrameworkTestBase {


    @Test
    public void createConcert_OK_onValidRequest() {
        //when
        ValidatableResponse response =
                RestAssured.given()
                        .port(8180)
                        .contentType(JSON)
                        .body(Maps.of(
                                "title", "concert-title",
                                "date", ofInstant(TEST_TIME_PLUS_30_DAYS, UTC).toString(),
                                "vendorId", "some-id"
                        ))
                        .post("api/v1/concert")
                        .then();

        //then
        response
                .statusCode(SC_OK);

        ConcertId concertId = ConcertAssert
                .assertThatConcert(concertFixtures.concertRepo.findAll().iterator().next())
                .isPersistedIn(concertFixtures.concertRepo, RepoEntries.SINGULAR)
                .hasIdAsUUID()
                .hasTitle("concert-title")
                .hasDate(TEST_TIME_PLUS_30_DAYS)
                .hasVendorId("some-id")
                .extractId();

        response
                .body("id", equalTo(concertId.asString()));
    }


    @Test
    public void createConcert_returnsBadRequest_onDateTooSoon() {
        //when
        ValidatableResponse response = concertFixtures.concertHttpClient.createConcert("concert-title", ofInstant(TEST_TIME, UTC).toString(), "some-id");

        //then
        ErrorJsonAssert.assertThatError(response)
                .isBadRequest("invalid_date");
    }



}
