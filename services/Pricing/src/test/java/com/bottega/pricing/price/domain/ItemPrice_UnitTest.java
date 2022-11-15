package com.bottega.pricing.price.domain;

import com.bottega.pricing.fixtures.LogicTestBase;
import com.bottega.pricing.price.fixtures.PriceAssert;
import com.bottega.sharedlib.fixtures.UUIDs;
import com.bottega.sharedlib.repo.MoneyDbEntity;
import com.bottega.sharedlib.vo.Money;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class ItemPrice_UnitTest extends LogicTestBase {

    @Test
    void applyPercentageFactor_discountsPrice_onValidRequest() {
        //given
        ItemPrice itemPrice = new ItemPrice(new PriceId(), MoneyDbEntity.from(new Money(200_00)), UUIDs.zeros(), new ArrayList<>());

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