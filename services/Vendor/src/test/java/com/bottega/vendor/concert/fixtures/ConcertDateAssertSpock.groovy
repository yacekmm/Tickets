package com.bottega.vendor.concert.fixtures

import com.bottega.sharedlib.vo.error.ErrorResult
import com.bottega.vendor.concert.domain.ConcertDate
import groovy.transform.TupleConstructor
import io.vavr.control.Validation
import spock.lang.Specification

import java.time.LocalDate

import static com.bottega.sharedlib.vo.error.ErrorType.BAD_REQUEST
import static com.bottega.vendor.concert.api.app.ConcertErrorCode.invalid_date

@TupleConstructor
class ConcertDateAssertSpock extends Specification {

    Validation<ErrorResult, ConcertDate> actualConcertDate

    static ConcertDateAssertSpock assertThatConcertDate(Validation<ErrorResult, ConcertDate> actualConcertDate) {
        new ConcertDateAssertSpock(actualConcertDate)
    }


    ConcertDateAssertSpock isEqualTo(LocalDate expectedDate) {
        assert actualConcertDate.isValid()
        assert actualConcertDate.get().utcDate == expectedDate
        this
    }

    ConcertDateAssertSpock hasInvalidDateError(String expectedErrorDesc) {
        assert actualConcertDate.isInvalid()
        with(actualConcertDate.getError()) {
            type == BAD_REQUEST
            code == invalid_date
            description == expectedErrorDesc
        }
        this
    }
}
