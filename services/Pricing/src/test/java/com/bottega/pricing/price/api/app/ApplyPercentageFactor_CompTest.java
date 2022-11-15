package com.bottega.pricing.price.api.app;

import com.bottega.pricing.fixtures.LogicTestBase;
import com.bottega.pricing.price.domain.ItemPrice;
import com.bottega.pricing.price.fixtures.*;
import com.bottega.sharedlib.fixtures.*;
import com.bottega.sharedlib.vo.error.ErrorResult;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.bottega.sharedlib.fixtures.RepoEntries.SINGULAR;
import static com.bottega.sharedlib.vo.error.ErrorType.NOT_FOUND;
import static com.bottega.sharedlib.vo.error.GenericErrorCode.not_found;
import static org.assertj.vavr.api.VavrAssertions.assertThat;
import static org.mockito.BDDMockito.then;

class ApplyPercentageFactor_CompTest extends LogicTestBase {

    @Test
    void applyPercentageFactor_returnsSingleDiscountedPrice_onValidRequest() {
        ItemPrice price = builders.dontLook().priceForItem(100_00, "item-id").inDb();

        //when
        Either<ErrorResult, List<ItemPrice>> result = priceFixtures.priceService.applyPercentageFactor("item-id", 10);

        //then
        assertThat(result).hasRightValueSatisfying(itemPrices ->
                PriceAssert.assertThatPrice(itemPrices.get(0))
                        .isPersistedIn(priceFixtures.itemPriceRepo, SINGULAR)
                        .hasPrice(90_00)
                        .hasId(price.getId())
                        .hasItemId(price.getItemId())
                        .hasFactors(1, factors ->
                                factors.forEach(factor ->
                                        PriceFactorAssert.assertThatFactor(factor)
                                                .isPercentageFactor(10, price)
                                ))
        );
    }

    @Test
    void applyPercentageFactor_publishesPriceChangeEvent_onPriceChange() {
        ItemPrice price = builders.dontLook().priceForItem(100_00, "item-id").inDb();

        //when
        Either<ErrorResult, List<ItemPrice>> result = priceFixtures.priceService.applyPercentageFactor("item-id", 10);

        //then
        assertThat(result).isRight();
        EventAssert.assertThatEventV1(sharedFixtures.fakeEventPublisher().singleEvent())
                .isPriceChange(90_00, price.getId().asString(), price.getItemId());
    }

    @Test
    void applyPercentageFactor_updatesReadModel_onPriceChange() {
        ItemPrice price = builders.dontLook().priceForItem(100_00, "item-id").inDb();

        //when
        Either<ErrorResult, List<ItemPrice>> result = priceFixtures.priceService.applyPercentageFactor("item-id", 10);

        //then
        assertThat(result).isRight();
        then(priceFixtures.priceUpdateService).should().updateReadModel(price);
    }

    @Test
    void applyPercentageFactor_returnsError_onNoPriceFound() {
        //when
        Either<ErrorResult, List<ItemPrice>> result = priceFixtures.priceService.applyPercentageFactor("not-existing-id", 10);

        //then
        assertThat(result)
                .hasLeftValueSatisfying(err ->
                        ErrorAssert.assertThatError(err)
                                .hasType(NOT_FOUND)
                                .hasCode(not_found)
                                .hasDescription("No price entries found for requested item. itemId: not-existing-id")
                );
    }
}