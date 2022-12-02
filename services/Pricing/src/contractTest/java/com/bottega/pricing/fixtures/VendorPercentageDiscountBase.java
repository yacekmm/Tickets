package com.bottega.pricing.fixtures;

import com.bottega.pricing.price.domain.ItemPrice;
import com.bottega.sharedlib.fixtures.UUIDs;
import org.junit.jupiter.api.BeforeEach;

public class VendorPercentageDiscountBase extends CdcFrameworkTestBase {

    protected ItemPrice price;

    @BeforeEach
    void setUp() {
        price = builders.dontLook().priceForItem(100_00, UUIDs.zeros()).inDb();
    }

}
