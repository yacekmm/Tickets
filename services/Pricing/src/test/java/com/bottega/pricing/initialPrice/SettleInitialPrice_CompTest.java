package com.bottega.pricing.initialPrice;

import com.bottega.pricing.fixtures.LogicTestBase;
import com.bottega.pricing.price.domain.ItemPrice;
import com.bottega.pricing.price.fixtures.PriceAssert;
import com.bottega.sharedlib.fixtures.EventAssert;
import com.bottega.sharedlib.vo.Money;
import com.bottega.sharedlib.vo.error.ErrorResult;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.assertj.vavr.api.VavrAssertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

class SettleInitialPrice_CompTest extends LogicTestBase {

    @Test
    public void settleInitialPrice_calculatesPrice_onValidInput() {
        //given
        int expectedPrice = 105_00;
        String expectedItemId = "item-id";

        given(initPriceFixtures.priceService.addNewPrice(eq(expectedItemId), eq(new Money(expectedPrice))))
                .willReturn(Either.right(priceFixtures.priceBuilder.priceForItem(expectedPrice, expectedItemId).build()));

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

    @Test
    void settleNewPrice_publishesPriceChangeEvent_onValidRequest() {
        given(initPriceFixtures.priceService.addNewPrice(anyString(), any(Money.class)))
                .willReturn(Either.right(priceFixtures.priceBuilder.priceForItem(105_00, "item-id").build()));

        //when
        Either<ErrorResult, ItemPrice> result = initPriceFixtures.initPriceService.settleInitialPrice("item-id", 5, new HashSet<>());

        //then
        assertThat(result).isRight();
        EventAssert.assertThatEventV1(sharedFixtures.fakeEventPublisher().singleEvent())
                .isPriceChange(105_00, result.get().getId().asString(), "item-id");
    }

}