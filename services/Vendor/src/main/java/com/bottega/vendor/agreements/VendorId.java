package com.bottega.vendor.agreements;

import com.bottega.sharedlib.repo.AggregateId;
import jakarta.persistence.Embeddable;


@Embeddable
public class VendorId extends AggregateId {
    public VendorId() {
        super(AggregateId.generate());
    }

    public VendorId(String idString) {
        super(idString);
    }
}
