package com.bottega.vendor.concert.api.rest;

import com.bottega.vendor.fixtures.FrameworkTestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.apache.groovy.util.Maps;
import org.junit.jupiter.api.Test;

import static com.bottega.sharedlib.config.TestClockConfig.TEST_TIME_PLUS_30_DAYS;
import static io.restassured.http.ContentType.JSON;
import static java.time.LocalDate.ofInstant;
import static java.time.ZoneOffset.UTC;

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

    }

}
