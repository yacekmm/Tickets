package com.bottega.vendor.agreements;

import org.springframework.stereotype.Component;

@Component
public class VendorService {

    //TODO: Component test
    public VendorAgreement getVendorAgreement(String vendorIdString) {
        //TODO: cheating a bit - any vendorId exists, and has margin 5. To-be-implemented :)
        return new VendorAgreement(new VendorId(vendorIdString), 5);
    }
}
