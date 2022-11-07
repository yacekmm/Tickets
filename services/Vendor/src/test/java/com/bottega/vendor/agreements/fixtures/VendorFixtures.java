package com.bottega.vendor.agreements.fixtures;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor //for Spring to Autowire
@NoArgsConstructor
public class VendorFixtures {

    //SUTs


    //infrastructure


    //builders
    public VendorAgreementBuilder vendorAgreementBuilder;

    //clients


    //services


    //mocks


    public static VendorFixtures init() {
        VendorFixtures vendorFixtures = new VendorFixtures();

        initBuilders(vendorFixtures);

        return vendorFixtures;
    }

    private static void initBuilders(VendorFixtures concertFixtures) {
        concertFixtures.vendorAgreementBuilder = new VendorAgreementBuilder();
    }


}
