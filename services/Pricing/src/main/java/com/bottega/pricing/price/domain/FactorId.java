package com.bottega.pricing.price.domain;

import com.bottega.sharedlib.repo.AggregateId;
import jakarta.persistence.Embeddable;


@Embeddable
public class FactorId extends AggregateId {

    public FactorId() {
        super(AggregateId.generate());
    }

    public FactorId(String factorId) {
        super(factorId);
    }

}
