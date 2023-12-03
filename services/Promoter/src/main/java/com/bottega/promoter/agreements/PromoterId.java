package com.bottega.promoter.agreements;

import com.bottega.sharedlib.repo.AggregateId;
import jakarta.persistence.Embeddable;


@Embeddable
public class PromoterId extends AggregateId {
    public PromoterId() {
        super(AggregateId.generate());
    }

    public PromoterId(String idString) {
        super(idString);
    }
}
