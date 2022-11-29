package com.bottega.vendor.concert.domain;

import com.bottega.sharedlib.repo.AggregateId;

import javax.persistence.Embeddable;

@Embeddable
public class TagId extends AggregateId {

    public TagId() {
        super(AggregateId.generate());
    }

    public TagId(String tagId) {
        super(tagId);
    }
}
