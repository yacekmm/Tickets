package com.bottega.vendor.concert.domain;

import com.bottega.sharedlib.ddd.ValueObject;
import com.bottega.sharedlib.vo.error.ErrorResult;
import io.vavr.control.*;
import lombok.*;

import javax.persistence.*;
import java.time.*;

import static com.bottega.sharedlib.vo.error.ErrorResult.badRequest;
import static com.bottega.vendor.concert.api.app.ConcertErrorCode.invalid_date;
import static java.time.ZoneOffset.UTC;
import static java.time.temporal.ChronoUnit.DAYS;

@ValueObject
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ConcertDate {


    private static final int MIN_DATE_THRESHOLD_DAYS = 7;

    @Column(name = "date")
    private Instant date;

    public static Validation<ErrorResult, ConcertDate> from(String date, Clock clock) {
        return Try.of(() -> LocalDate.parse(date).atStartOfDay().toInstant(UTC))
                .orElse(Try.of(() -> Instant.parse(date)))
                .toValidation()
                .mapError(throwable -> badRequest(invalid_date, "Unsupported date format: %s", throwable.getMessage()))
                .filter(instant -> instant.isAfter(clock.instant().plus(MIN_DATE_THRESHOLD_DAYS, DAYS)))
                .getOrElse(Validation.invalid(badRequest(invalid_date, "Too early")))
                .map(ConcertDate::new);
    }

    public LocalDate getUtcDate() {
        return LocalDate.ofInstant(date, UTC);
    }

}
