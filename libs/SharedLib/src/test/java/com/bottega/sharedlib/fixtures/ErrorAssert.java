package com.bottega.sharedlib.fixtures;

import com.bottega.sharedlib.vo.error.ErrorCode;
import com.bottega.sharedlib.vo.error.ErrorResult;
import com.bottega.sharedlib.vo.error.ErrorType;
import lombok.RequiredArgsConstructor;

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
}
