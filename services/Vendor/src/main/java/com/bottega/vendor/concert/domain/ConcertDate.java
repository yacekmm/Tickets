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
    @Column(name = "date")
    private Instant date;

    public static ConcertDate from(String date) {
        //TODO/JM: handle dateString exception
        return new ConcertDate(Instant.parse(date));
    }
}
