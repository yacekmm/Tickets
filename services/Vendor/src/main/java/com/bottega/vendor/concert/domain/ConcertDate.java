package com.bottega.vendor.concert.domain;

import com.bottega.sharedlib.ddd.ValueObject;
import com.bottega.sharedlib.vo.error.ErrorResult;
import io.vavr.control.Validation;
import lombok.*;

import javax.persistence.*;
import java.time.*;

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
        return null;
    }

    public LocalDate getUtcDate() {
        return LocalDate.ofInstant(date, UTC);
    }

}
