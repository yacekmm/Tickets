package com.bottega.vendor.concert.domain;

import com.bottega.vendor.shared.AggregateId;

import javax.persistence.Embeddable;

@Embeddable
public class ConcertId extends AggregateId {

    public ConcertId() {
        super(AggregateId.generate());
    }

}
