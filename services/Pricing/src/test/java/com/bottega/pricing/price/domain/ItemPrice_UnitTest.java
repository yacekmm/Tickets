package com.bottega.pricing.price.domain;

import com.bottega.pricing.price.fixtures.PriceAssert;
import com.bottega.pricing.price.fixtures.PriceLogicTestBase;
import org.junit.jupiter.api.Test;

class ItemPrice_UnitTest extends PriceLogicTestBase {

    @Test
    void applyPercentageFactor_discountsPrice_onValidRequest() {
        //given
        ItemPrice itemPrice = priceFixtures.priceBuilder.build();

        //when
        ItemPrice result = itemPrice.applyFactor(PriceFactorFactory.percentageFactor(10, itemPrice));

        //then
        PriceAssert.assertThatPrice(result)
                .hasPrice(180_00)
                .hasId(itemPrice.getId())
                .hasItemId(itemPrice.getItemId());
    }

    //TODO: validate percentage input param <0, >100, ...
    //TODO: assert that price has list of applied factors
}