package com.bottega.vendor.concert.fixtures;

import com.bottega.sharedlib.vo.error.*;
import com.bottega.vendor.concert.domain.Title;
import io.vavr.control.Validation;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;

import static com.bottega.sharedlib.vo.error.ErrorType.BAD_REQUEST;
import static com.bottega.vendor.concert.api.app.ConcertErrorCode.invalid_title;
import static org.assertj.vavr.api.VavrAssertions.assertThat;

@RequiredArgsConstructor(staticName = "assertThatTitle")
public class TitleAssert {

    private final Validation<ErrorResult, Title> title;


    public TitleAssert hasValue(String expectedValue) {

        assertThat(title).isValid();
        Assertions.assertThat(title.get().getValue()).isEqualTo(expectedValue);
        return this;
    }

    public TitleAssert isValid() {
        assertThat(title).isValid();
        return this;
    }

    public TitleAssert hasInvalidLengthError() {
        return hasError(BAD_REQUEST, invalid_title, "Title length must be between 10 and 160 chars");
    }

    public TitleAssert hasBannedWordsError(String bannedWord) {
        return hasError(BAD_REQUEST, invalid_title, "Title must not contain banned word: " + bannedWord);
    }

    private TitleAssert hasError(ErrorType errorType, ErrorCode errorCode, String description) {
        assertThat(title).isInvalid();
        Assertions.assertThat(title.getError().getType()).isEqualTo(errorType);
        Assertions.assertThat(title.getError().getCode()).isEqualTo(errorCode);
        Assertions.assertThat(title.getError().getDescription()).isEqualTo(description);
        return this;
    }
}
