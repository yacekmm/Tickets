package com.bottega.vendor.concert.domain;

import com.bottega.sharedlib.ddd.ValueObject;
import com.bottega.sharedlib.vo.error.ErrorResult;
import io.vavr.control.Try;
import io.vavr.control.Validation;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;

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

    @Column(name = "date")
    private Instant date;

    private static final int MIN_DATE_THRESHOLD_DAYS = 7;

    public static Validation<ErrorResult, ConcertDate> from(String date, Clock clock) {
        return Try.of(() -> Instant.parse(date))
                .orElse(Try.ofSupplier(() -> LocalDate.parse(date).atStartOfDay().toInstant(UTC)))
                .toValidation()
                .mapError(throwable -> ErrorResult.badRequest(invalid_date, "Unsupported date format: " + throwable.getMessage()))
                .filter(parsed -> parsed.isAfter(clock.instant().plus(MIN_DATE_THRESHOLD_DAYS, DAYS)))
                .getOrElse(Validation.invalid(ErrorResult.badRequest(invalid_date, "Too early")))
                .map(ConcertDate::new);
    }

    public LocalDate getUtcDate() {
        return LocalDate.ofInstant(date, UTC);
    }
}
