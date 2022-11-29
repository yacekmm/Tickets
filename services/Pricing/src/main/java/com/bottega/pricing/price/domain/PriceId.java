package com.bottega.pricing.price.domain;

import com.bottega.sharedlib.repo.AggregateId;

import javax.persistence.Embeddable;

@Embeddable
public class PriceId extends AggregateId {

    public PriceId() {
        super(AggregateId.generate());
    }

    public PriceId(String priceId) {
        super(priceId);
    }

}
