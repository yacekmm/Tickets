package com.bottega.pricing.fixtures;

import com.bottega.pricing.price.domain.ItemPrice;
import com.bottega.sharedlib.fixtures.UUIDs;
import org.junit.jupiter.api.BeforeEach;

public class MessagingPriceChangeBase extends CdcFrameworkTestBase {

    protected ItemPrice price;

    @BeforeEach
    void setUp() {
        price = priceFixtures.priceBuilder.priceForItem(100_00, UUIDs.zeros()).inDb();
    }

    protected void applyPercentageDiscount(int percentage) {
        priceFixtures.factorClient.applyPercentageFactor(price.getItemId(), percentage);
    }
}
