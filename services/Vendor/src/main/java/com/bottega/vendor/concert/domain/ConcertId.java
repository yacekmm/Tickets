package com.bottega.vendor.concert.domain;

import com.bottega.sharedlib.repo.AggregateId;

import javax.persistence.Embeddable;

@Embeddable
public class ConcertId extends AggregateId {

    public ConcertId() {
        super(AggregateId.generate());
    }

    public ConcertId(String concertId) {
        super(concertId);
    }
}
