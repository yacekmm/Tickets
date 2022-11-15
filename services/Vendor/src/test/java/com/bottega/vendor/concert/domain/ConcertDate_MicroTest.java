package com.bottega.vendor.concert.domain;

import com.bottega.sharedlib.vo.error.ErrorResult;
import com.bottega.vendor.concert.fixtures.ConcertLogicTestBase;
import io.vavr.control.Validation;
import org.junit.jupiter.api.Test;

import static com.bottega.sharedlib.vo.error.ErrorType.BAD_REQUEST;
import static com.bottega.vendor.concert.api.app.ConcertErrorCode.invalid_date;
import static java.time.LocalDate.of;
import static org.assertj.core.api.Assertions.assertThat;

class ConcertDate_MicroTest extends ConcertLogicTestBase {

    @Test
    public void fromString_OK_onDateString(){
        //expect
        assertThat(ConcertDate.from("2022-02-22", sharedFixtures.clock).get().getUtcDate()).isEqualTo(of(2022, 2, 22));
    }

    @Test
    public void fromString_OK_onDateTimeString(){
        //expect
        assertThat(ConcertDate.from("2022-02-22T22:11:00Z", sharedFixtures.clock).get().getUtcDate()).isEqualTo(of(2022, 2, 22));
    }


    @Test
    public void fromString_returnsError_onInvalidString(){
        //when
        Validation<ErrorResult, ConcertDate> result = ConcertDate.from("invalid", sharedFixtures.clock);

        //then
        assertThat(result.getError().getType()).isEqualTo(BAD_REQUEST);
        assertThat(result.getError().getCode()).isEqualTo(invalid_date);
        assertThat(result.getError().getDescription()).startsWith("Unsupported date format:");
    }
}