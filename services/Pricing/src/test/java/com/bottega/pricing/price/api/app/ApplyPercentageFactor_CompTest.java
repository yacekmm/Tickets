package com.bottega.pricing.price.api.app;

import com.bottega.pricing.fixtures.LogicTestBase;
import com.bottega.pricing.price.domain.ItemPrice;
import com.bottega.pricing.price.fixtures.*;
import com.bottega.sharedlib.fixtures.ErrorAssert;
import com.bottega.sharedlib.vo.error.ErrorResult;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.bottega.sharedlib.fixtures.RepoEntries.SINGULAR;
import static org.assertj.vavr.api.VavrAssertions.assertThat;

class ApplyPercentageFactor_CompTest extends LogicTestBase {

    @Test
    void applyPercentageFactor_returnsSingleDiscountedPrice_onValidRequest() {
        //given
        ItemPrice price = builders.aPrice().priceForItem(100_00, "item-id").build();
        priceFixtures.itemPriceRepo.save(price);

        //when
        Either<ErrorResult, List<ItemPrice>> result = priceFixtures.priceService.applyPercentageFactor("item-id", 10);

        //then
        assertThat(result).hasRightValueSatisfying(itemPrices ->
                PriceAssert.assertThatPrice(itemPrices.get(0))
                        .isPersistedIn(priceFixtures.itemPriceRepo, SINGULAR)
                        .hasItemId("item-id")
                        .hasPrice(90_00)
                        .hasFactors(1, factors ->
                                factors.forEach(factor ->
                                        PriceFactorAssert.assertThatFactor(factor)
                                                .isPercentageFactor(10, price)))
        );
    }

    @Test
    void applyPercentageFactor_returnsError_onNoPriceFound() {
        //when
        Either<ErrorResult, List<ItemPrice>> result = priceFixtures.priceService.applyPercentageFactor("item-id", 10);

        //then
        assertThat(result).hasLeftValueSatisfying(errorResult ->
                ErrorAssert.assertThatError(errorResult)
                        .isNotFound("No price entries found for requested item. itemId: item-id"));
    }
}