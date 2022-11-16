package com.bottega.sharedlib.fixtures;

import com.bottega.sharedlib.vo.error.*;
import io.restassured.response.ValidatableResponse;
import lombok.RequiredArgsConstructor;

import static com.bottega.sharedlib.vo.error.ErrorType.BAD_REQUEST;
import static com.bottega.sharedlib.vo.error.GenericErrorCode.invalid_request;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.hamcrest.Matchers.equalTo;

@RequiredArgsConstructor(staticName = "assertThatError")
public class ErrorJsonAssert {

    private final ValidatableResponse errorResponse;

    public ErrorJsonAssert hasType(ErrorType expectedType) {
        errorResponse.body("type", equalTo(expectedType.name()));
        return this;
    }

    public ErrorJsonAssert hasCode(ErrorCode expectedCode) {
        errorResponse.body("code", equalTo(expectedCode.toString()));
        return this;
    }

    public ErrorJsonAssert hasDescription(String expectedDesc) {
        errorResponse.body("description", equalTo(expectedDesc));
        return this;
    }

    public ErrorJsonAssert isBadRequest(String expectedDesc) {
        errorResponse.statusCode(SC_BAD_REQUEST);
        return hasType(BAD_REQUEST)
                .hasCode(invalid_request)
                .hasDescription(expectedDesc);
    }
}
