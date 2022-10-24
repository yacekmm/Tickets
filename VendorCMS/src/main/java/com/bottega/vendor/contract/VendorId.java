package com.bottega.vendor.contract;

import com.bottega.vendor.shared.AggregateId;

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
