package com.bottega.vendor.agreements.fixtures;

import com.bottega.vendor.agreements.*;

public class VendorAgreementBuilder {

    private final VendorAgreement.VendorAgreementBuilder builder;

    public VendorAgreementBuilder() {
        this.builder = VendorAgreement.builder()
                .vendorId(new VendorId("some-vendorId"))
                .profitMarginPercentage(33);
    }

    public VendorAgreementBuilder forVendor(String vendorId){
        builder.vendorId(new VendorId(vendorId));
        return this;
    }

    public VendorAgreement build() {
        return builder.build();
    }
}
