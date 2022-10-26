package com.bottega.pricing.tests;

import com.bottega.sharedlib.tests.UUIDs;
import org.junit.jupiter.api.BeforeEach;

public class VendorPercentageDiscountBase extends CdcFrameworkTestBase {

    @BeforeEach
    void setUp() {
        priceFixtures.priceBuilder.priceForItem(100_00, UUIDs.zeros()).inDb();
    }
}
