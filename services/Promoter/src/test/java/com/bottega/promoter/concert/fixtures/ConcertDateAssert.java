package com.bottega.promoter.concert.fixtures;

import com.bottega.promoter.concert.domain.ConcertDate;
import com.bottega.sharedlib.vo.error.ErrorResult;
import io.vavr.control.Validation;
import lombok.RequiredArgsConstructor;
import static com.bottega.promoter.concert.api.app.ConcertErrorCode.invalid_date;
import static com.bottega.sharedlib.vo.error.ErrorType.BAD_REQUEST;
import static java.time.LocalDate.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.vavr.api.VavrAssertions.assertThat;

@RequiredArgsConstructor(staticName = "assertThatConcertDate")
public class ConcertDateAssert {

    private final Validation<ErrorResult, ConcertDate> concertDate;


    public ConcertDateAssert isEqualTo(int year, int month, int day) {

        assertThat(concertDate).isValid();
        assertThat(concertDate.get().getUtcDate()).isEqualTo(of(year, month, day));
        return this;
    }

    public ConcertDateAssert hasInvalidDateError(String description) {
        assertThat(concertDate).isInvalid();
        assertThat(concertDate.getError().getType()).isEqualTo(BAD_REQUEST);
        assertThat(concertDate.getError().getCode()).isEqualTo(invalid_date);
        assertThat(concertDate.getError().getDescription()).isEqualTo(description);
        return this;
    }

    public ConcertDateAssert isValid() {
        assertThat(concertDate).isValid();
        return this;
    }
}
