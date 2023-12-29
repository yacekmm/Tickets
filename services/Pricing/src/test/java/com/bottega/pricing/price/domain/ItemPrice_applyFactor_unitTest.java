package com.bottega.pricing.price.domain;

import java.util.ArrayList;

import com.bottega.pricing.fixtures.LogicTestBase;
import com.bottega.pricing.price.fixtures.PriceAssert;
import com.bottega.sharedlib.fixtures.UUIDs;
import com.bottega.sharedlib.repo.MoneyDbEntity;
import com.bottega.sharedlib.vo.Money;
import org.junit.jupiter.api.Test;
import static com.bottega.pricing.price.fixtures.PriceAssert.assertThatPrice;

class ItemPrice_applyFactor_unitTest extends LogicTestBase {

    @Test
    void applyPercentageFactor_discountsPrice_onValidRequest() {
        //given
        //TODO use builder
        ItemPrice itemPrice = new ItemPrice(new PriceId(), MoneyDbEntity.from(new Money(200_00)), UUIDs.zeros(), new ArrayList<>());

        //when
        ItemPrice result = itemPrice.applyFactor(PriceFactorFactory.percentageFactor(10, itemPrice));

        //then
        assertThatPrice(result)
                .hasPrice(180_00)
                .hasId(itemPrice.getId())
                .hasItemId(itemPrice.getItemId());
    }

}