package com.bottega.vendor.concert.domain;

import com.bottega.sharedlib.ddd.ValueObject;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.Instant;

@ValueObject
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ConcertDate {

    @Getter
    @Column(name = "dateTime")
    private Instant dateTime;

    public static ConcertDate from(String date) {
        return new ConcertDate(Instant.parse(date));
    }
}
