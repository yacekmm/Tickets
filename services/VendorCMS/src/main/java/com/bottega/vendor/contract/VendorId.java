package com.bottega.vendor.contract;

import com.bottega.sharedlib.repo.AggregateId;

import javax.persistence.Embeddable;

@Embeddable
public class VendorId extends AggregateId {
    public VendorId() {
        super(AggregateId.generate());
    }

    public VendorId(String idString) {
        super(idString);
    }
}
