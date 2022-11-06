package com.bottega.vendor.concert.domain;

import com.bottega.sharedlib.repo.AggregateId;

import javax.persistence.Embeddable;

@Embeddable
public class CategoryId extends AggregateId {

    public CategoryId() {
        super(AggregateId.generate());
    }

    public CategoryId(String id) {
        super(id);
    }
}
