package com.bottega.vendor.concert.fixtures

import com.bottega.sharedlib.vo.error.ErrorResult
import com.bottega.vendor.concert.domain.Title
import groovy.transform.TupleConstructor
import io.vavr.control.Validation
import spock.lang.Specification

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

}
