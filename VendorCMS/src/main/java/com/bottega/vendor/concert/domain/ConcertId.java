package com.bottega.vendor.concert.domain;

import com.bottega.vendor.shared.AggregateId;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
@Embeddable
@EqualsAndHashCode
@ToString
public class ConcertId implements AggregateId {

    @Getter
    @Column(insertable = false, updatable = false, name = "id")
    protected String value;

    public static ConcertId generate() {
        return new ConcertId(UUID.randomUUID().toString());
    }

}
