package com.bottega.pricing.price.domain;

import com.bottega.pricing.fixtures.LogicTestBase;
import com.bottega.pricing.price.fixtures.*;
import org.junit.jupiter.api.*;

class ItemPrice_UnitTest extends LogicTestBase {

    private ItemPriceBuilder itemPriceBuilder;

    @BeforeEach
    void setUp() {
        itemPriceBuilder = new ItemPriceBuilder();
    }

    @Test
    void applyPercentageFactor_discountsPrice_onValidRequest() {
        //given
        ItemPrice itemPrice = itemPriceBuilder
                .withPrice(200_00)
                .build();

        //when
        ItemPrice result = itemPrice.applyFactor(PriceFactorFactory.percentageFactor(10, itemPrice));

        //then
        PriceAssert.assertThatPrice(result)
                .hasPrice(180_00)
                .hasId(itemPrice.getId())
                .hasItemId(itemPrice.getItemId());
    }

    //validate percentage input param <0, >100, ...
    //assert that price has list of applied factors
}