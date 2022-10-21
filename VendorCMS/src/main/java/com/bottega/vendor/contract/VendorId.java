package com.bottega.vendor.contract;

import com.bottega.vendor.shared.AggregateId;

public class VendorId extends AggregateId {

    public VendorId() {
        super(AggregateId.generate());
    }
    
}
