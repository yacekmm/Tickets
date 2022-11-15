package com.bottega.vendor.concert.domain;

import com.bottega.sharedlib.ddd.ValueObject;
import com.bottega.sharedlib.vo.error.ErrorResult;
import com.bottega.vendor.concert.api.app.ConcertErrorCode;
import io.vavr.control.Validation;
import lombok.*;

import javax.persistence.*;
import java.time.*;
import java.time.format.DateTimeParseException;

import static java.time.ZoneOffset.UTC;

@ValueObject
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ConcertDate {


    @Column(name = "date")
    private Instant date;

    public static Validation<ErrorResult, ConcertDate> from(String date, Clock clock) {
        try {
            return Validation.valid(new ConcertDate(LocalDate.parse(date).atStartOfDay().toInstant(UTC)));
        } catch (DateTimeParseException e) {
            return Validation.invalid(ErrorResult.badRequest(ConcertErrorCode.invalid_date, "Unsupported date format: %s", e.getMessage()));
        }
    }

    public LocalDate getUtcDate() {
        return LocalDate.ofInstant(date, UTC);
    }

}
