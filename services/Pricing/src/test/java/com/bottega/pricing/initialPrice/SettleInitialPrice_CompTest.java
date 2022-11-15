package com.bottega.pricing.initialPrice;

import com.bottega.pricing.fixtures.LogicTestBase;
import com.bottega.pricing.price.domain.ItemPrice;
import com.bottega.pricing.price.fixtures.PriceAssert;
import com.bottega.sharedlib.vo.Money;
import com.bottega.sharedlib.vo.error.ErrorResult;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.assertj.vavr.api.VavrAssertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

class SettleInitialPrice_CompTest extends LogicTestBase {

    @Test
    public void settleInitialPrice_calculatesPrice_onValidInput() {
        //given
        int expectedPrice = 105_00;
        String expectedItemId = "item-id";

        given(initPriceFixtures.priceService.addNewPrice(eq(expectedItemId), eq(new Money(expectedPrice))))
                .willReturn(Either.right(builders.dontLook().priceForItem(expectedPrice, expectedItemId).build()));

        //when
        Either<ErrorResult, ItemPrice> result = initPriceFixtures.initPriceService.settleInitialPrice(expectedItemId, 5, new HashSet<>());

        //then
        assertThat(result).hasRightValueSatisfying(itemPrice ->
                PriceAssert.assertThatPrice(itemPrice)
                        .hasPrice(expectedPrice)
                        .hasItemId(expectedItemId)
                        .hasNoFactors()
        );
    }
}

