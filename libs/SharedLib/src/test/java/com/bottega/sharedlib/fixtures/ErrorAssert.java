package com.bottega.sharedlib.fixtures;

import com.bottega.sharedlib.vo.error.*;
import lombok.RequiredArgsConstructor;

import static com.bottega.sharedlib.vo.error.ErrorType.NOT_FOUND;
import static com.bottega.sharedlib.vo.error.GenericErrorCode.not_found;
import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor(staticName = "assertThatError")
public class ErrorAssert {

    private final ErrorResult error;

    public ErrorAssert hasType(ErrorType expectedType) {
        assertThat(error.getType()).isEqualTo(expectedType);
        return this;
    }

    public ErrorAssert hasCode(ErrorCode expectedCode) {
        assertThat(error.getCode()).isEqualTo(expectedCode);
        return this;
    }

    public ErrorAssert hasDescription(String expectedDesc) {
        assertThat(error.getDescription()).isEqualTo(expectedDesc);
        return this;
    }

    public ErrorAssert isNotFound(String expectedDescription) {
        return hasType(NOT_FOUND)
                .hasCode(not_found)
                .hasDescription(expectedDescription);
    }
}
