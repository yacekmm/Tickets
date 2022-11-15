package com.bottega.vendor.concert.fixtures;

import com.bottega.sharedlib.vo.error.ErrorResult;
import com.bottega.vendor.concert.domain.ConcertDate;
import io.vavr.control.Validation;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;

import static com.bottega.sharedlib.vo.error.ErrorType.BAD_REQUEST;
import static com.bottega.vendor.concert.api.app.ConcertErrorCode.invalid_date;
import static java.time.LocalDate.of;
import static org.assertj.vavr.api.VavrAssertions.assertThat;

@RequiredArgsConstructor(staticName = "assertThatConcertDate")
public class ConcertDateAssert {

    private final Validation<ErrorResult, ConcertDate> concertDate;


    public ConcertDateAssert isEqualTo(int year, int month, int day) {

        assertThat(concertDate).isValid();
        Assertions.assertThat(concertDate.get().getUtcDate()).isEqualTo(of(year, month, day));
        return this;
    }

    public ConcertDateAssert hasInvalidDateError(String description) {
        assertThat(concertDate).isInvalid();
        Assertions.assertThat(concertDate.getError().getType()).isEqualTo(BAD_REQUEST);
        Assertions.assertThat(concertDate.getError().getCode()).isEqualTo(invalid_date);
        Assertions.assertThat(concertDate.getError().getDescription()).isEqualTo(description);
        return this;
    }

    public ConcertDateAssert isValid() {
        assertThat(concertDate).isValid();
        return this;
    }
}
