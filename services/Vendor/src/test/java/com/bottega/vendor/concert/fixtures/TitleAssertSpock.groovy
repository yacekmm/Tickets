package com.bottega.vendor.concert.fixtures

import com.bottega.sharedlib.vo.error.ErrorResult
import com.bottega.vendor.concert.domain.Title
import groovy.transform.TupleConstructor
import io.vavr.control.Validation
import spock.lang.Specification

import static com.bottega.sharedlib.vo.error.ErrorType.BAD_REQUEST
import static com.bottega.vendor.concert.api.app.ConcertErrorCode.invalid_title

@TupleConstructor
class TitleAssertSpock extends Specification {

    Validation<ErrorResult, Title> actualTitle

    static TitleAssertSpock assertThatTitle(Validation<ErrorResult, Title> actual) {
        new TitleAssertSpock(actual)
    }

    TitleAssertSpock isEqualTo(String expectedTitle) {
        assert actualTitle.isValid()
        assert actualTitle.get().value == expectedTitle
        this
    }

    TitleAssertSpock hasInvalidLengthError() {
        hasInvalidTitleError("Title length must be between 10 and 160 chars")
    }

    TitleAssertSpock hasBannedWordError(String expectedBannedWord) {
        hasInvalidTitleError("Title must not contain banned word: " + expectedBannedWord)
    }

    protected TitleAssertSpock hasInvalidTitleError(String expectedErrorDesc) {
        assert actualTitle.isInvalid()
        with(actualTitle.getError()) {
            type == BAD_REQUEST
            code == invalid_title
            description == expectedErrorDesc
        }
        this
    }
}
