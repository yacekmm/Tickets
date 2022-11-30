package com.bottega.vendor.config;

import com.bottega.sharedlib.fixtures.ErrorJsonAssert;
import com.bottega.vendor.fixtures.FrameworkTestBase;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;
import static com.bottega.sharedlib.vo.error.ErrorType.BAD_REQUEST;
import static com.bottega.sharedlib.vo.error.GenericErrorCode.illegal_argument;

class ErrorExceptionMapper_convertToResponse_apiTest extends FrameworkTestBase {

    @Test
    public void convertToResponse_OK(){
        //when
        ValidatableResponse response = builders.aRequestSpec().get("/exception").then();

        //then
        ErrorJsonAssert.assertThatError(response)
                .hasType(BAD_REQUEST)
                .hasCode(illegal_argument)
                .hasDescription("make it a nice json, please");
    }
}