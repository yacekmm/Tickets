package com.bottega.pricing.tests;

import com.bottega.pricing.price.domain.ItemPrice;
import com.bottega.sharedlib.tests.UUIDs;
import org.junit.jupiter.api.BeforeEach;

public class VendorPercentageDiscountBase extends CdcFrameworkTestBase {

    protected ItemPrice price;

    @BeforeEach
    void setUp() {
        price = priceFixtures.priceBuilder.priceForItem(100_00, UUIDs.zeros()).inDb();
    }

}
