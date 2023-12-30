package com.bottega.promoter.concert.api.rest;

import com.bottega.promoter.concert.domain.ConcertId;
import com.bottega.promoter.concert.fixtures.asserts.ConcertAssert;
import com.bottega.promoter.concert.fixtures.clients.ConcertHttpClient;
import com.bottega.promoter.fixtures.FrameworkTestBase;
import com.bottega.sharedlib.fixtures.*;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;
import static com.bottega.promoter.concert.fixtures.asserts.ConcertAssert.assertThatConcert;
import static com.bottega.sharedlib.config.TestClockConfig.*;
import static com.bottega.sharedlib.fixtures.ErrorJsonAssert.assertThatError;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;

public class CreateConcertRestController_createConcert_restApiTest extends FrameworkTestBase {


    @Test
    public void createConcert_OK_onValidRequest() {
        //TODO implement
        //given

        //when

        //then
        //TODO assert http status code
        //TODO assert response body
        //TODO assert db state
    }


    @Test
    public void createConcert_returnsBadRequest_onDateTooSoon() {
        //TODO implement
        //given

        //when

        //then

    }



}
