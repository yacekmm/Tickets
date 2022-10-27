package com.bottega.pricing.initialPrice;

import com.bottega.pricing.fixtures.LogicTestBase;
import com.bottega.pricing.price.domain.ItemPrice;
import com.bottega.pricing.price.fixtures.PriceAssert;
import com.bottega.sharedlib.vo.Money;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

class SettleInitialPrice_CompTest extends LogicTestBase {

    @Test
    public void settleInitialPrice_calculatesPrice_onValidInput(){
        //given
        int expectedPrice = 105_00;
        String expectedItemId = "item-id";

        given(initPriceFixtures.priceService.addPrice(eq(expectedItemId), eq(new Money(expectedPrice))))
                .willReturn(priceFixtures.priceBuilder.priceForItem(expectedPrice, expectedItemId).build());

        //when
        ItemPrice initialPrice = initPriceFixtures.initPriceService.settleInitialPrice(expectedItemId, 5, new HashSet<>());

        //then
        PriceAssert.assertThatPrice(initialPrice)
                .hasPrice(expectedPrice)
                .hasItemId(expectedItemId)
                .hasFactors(0, unused -> {});
    }

}