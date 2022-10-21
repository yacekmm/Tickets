package com.bottega.vendor.concert.domain;

import com.bottega.vendor.shared.AggregateId;

public class ConcertId extends AggregateId {


    public ConcertId() {
        super(AggregateId.generate());
    }
    
}
