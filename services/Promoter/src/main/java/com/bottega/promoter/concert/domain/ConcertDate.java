package com.bottega.promoter.concert.domain;

import java.time.*;

import com.bottega.promoter.concert.api.app.ConcertErrorCode;
import com.bottega.sharedlib.ddd.ValueObject;
import com.bottega.sharedlib.vo.error.ErrorResult;
import io.vavr.control.*;
import jakarta.persistence.*;
import lombok.*;
import static com.bottega.sharedlib.vo.error.ErrorResult.badRequest;
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
        //TODO implementation
        return Validation.valid(new ConcertDate(LocalDate.parse(date).atStartOfDay().toInstant(UTC)));
    }

    public LocalDate getUtcDate() {
        return LocalDate.ofInstant(date, UTC);
    }

}
