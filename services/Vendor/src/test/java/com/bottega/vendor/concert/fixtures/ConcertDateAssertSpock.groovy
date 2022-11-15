package com.bottega.vendor.concert.fixtures

import com.bottega.sharedlib.vo.error.ErrorResult
import com.bottega.vendor.concert.domain.ConcertDate
import groovy.transform.TupleConstructor
import io.vavr.control.Validation
import spock.lang.Specification

@TupleConstructor
class ConcertDateAssertSpock extends Specification {

    Validation<ErrorResult, ConcertDate> actualConcertDate

    static ConcertDateAssertSpock assertThatConcertDate(Validation<ErrorResult, ConcertDate> actualConcertDate) {
        new ConcertDateAssertSpock(actualConcertDate)
    }

}
