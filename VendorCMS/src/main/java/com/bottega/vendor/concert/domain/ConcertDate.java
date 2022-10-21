package com.bottega.vendor.concert.domain;

import com.bottega.vendor.shared.ddd.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.Instant;

@ValueObject
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class ConcertDate {

    @Getter
    @Column(name = "dateTime")
    private Instant dateTime;

    public static ConcertDate from(String date) {
        return new ConcertDate(
                Instant.parse(date));
    }
}
