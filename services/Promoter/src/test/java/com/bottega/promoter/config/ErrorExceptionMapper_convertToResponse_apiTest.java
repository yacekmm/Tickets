package com.bottega.promoter.config;

import com.bottega.promoter.fixtures.FrameworkTestBase;
import com.bottega.sharedlib.fixtures.ErrorJsonAssert;
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