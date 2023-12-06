package com.bottega.pricing.price.domain;

import com.bottega.pricing.fixtures.LogicTestBase;
import com.bottega.pricing.price.fixtures.PriceAssert;
import org.junit.jupiter.api.Test;

class ItemPrice_applyFactor_unitTest extends LogicTestBase {

    @Test
    void applyPercentageFactor_discountsPrice_onValidRequest() {
        //given
        ItemPrice itemPrice = builders.aPrice().build();

        //when
        ItemPrice result = itemPrice.applyFactor(PriceFactorFactory.percentageFactor(10, itemPrice));

        //then
        PriceAssert.assertThatPrice(result)
                .hasPrice(180_00)
                .hasId(itemPrice.getId())
                .hasItemId(itemPrice.getItemId());
    }

}