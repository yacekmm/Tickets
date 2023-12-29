package com.bottega.promoter.concert.fixtures

import com.bottega.promoter.concert.domain.Title
import com.bottega.sharedlib.vo.error.ErrorResult
import groovy.transform.TupleConstructor
import io.vavr.control.Validation
import spock.lang.Specification

import static com.bottega.promoter.concert.api.app.ConcertErrorCode.invalid_title
import static com.bottega.sharedlib.vo.error.ErrorType.BAD_REQUEST

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

    //TODO assert methods
}
