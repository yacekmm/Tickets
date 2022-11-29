package com.bottega.vendor.agreements;

import org.springframework.stereotype.Component;

@Component
public class VendorService {

    public VendorAgreement getVendorAgreement(String vendorIdString) {
        //cheating a bit - any vendorId exists, and has margin 5
        return new VendorAgreement(new VendorId(vendorIdString), 5);
    }
}
