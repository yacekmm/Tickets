package com.bottega.pricing.fixtures;

import com.bottega.pricing.price.domain.ItemPrice;
import com.bottega.sharedlib.fixtures.UUIDs;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashSet;

public class MessagingPriceChangeBase extends CdcFrameworkTestBase {

    protected ItemPrice price;

    @BeforeEach
    void setUp() {
        //given
        price = builders.aPrice().priceForItem(100_00, UUIDs.zeros()).inDb();
    }

    protected void applyPercentageDiscount(int percentage) {
        priceFixtures.pricingHttpClient.applyPercentageFactor(price.getItemId(), percentage);
    }
    
    protected void settleInitialPrice(){
        initPriceFixtures.initPriceService.settleInitialPrice(price.getItemId(), 5, new HashSet<>());
    }
}
