package com.bottega.pricing.price.api.app;

import java.util.List;

import com.bottega.pricing.fixtures.LogicTestBase;
import com.bottega.pricing.price.domain.ItemPrice;
import com.bottega.sharedlib.vo.error.ErrorResult;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;
import static com.bottega.pricing.fixtures.PriceChangeEventAssert.assertThatEvent;
import static com.bottega.pricing.price.fixtures.PriceAssert.assertThatPrice;
import static com.bottega.pricing.price.fixtures.PriceFactorAssert.assertThatFactor;
import static com.bottega.sharedlib.fixtures.ErrorAssert.assertThatError;
import static com.bottega.sharedlib.fixtures.RepoEntries.SINGULAR;
import static org.assertj.vavr.api.VavrAssertions.assertThat;
import static org.mockito.BDDMockito.then;

class PriceService_applyPercentageFactor_compTest extends LogicTestBase {

    @Test
    void applyPercentageFactor_returnsSingleDiscountedPrice_onValidRequest() {
        //given
        ItemPrice price = builders.aPrice().priceForItem(100_00, "item-id").inDb();

        //when
        Either<ErrorResult, List<ItemPrice>> result = priceFixtures.priceService.applyPercentageFactor("item-id", 10);

        //then
        assertThat(result).hasRightValueSatisfying(itemPrices ->
                assertThatPrice(itemPrices.get(0))
                        .isPersistedIn(priceFixtures.itemPriceRepo, SINGULAR)
                        .hasPrice(90_00)
                        .hasId(price.getId())
                        .hasItemId(price.getItemId())
                        .hasFactors(1, factors ->
                                factors.forEach(factor ->
                                        assertThatFactor(factor)
                                                .isPercentageFactor(10, price)
                                ))
        );
    }

    @Test
    void applyPercentageFactor_publishesPriceChangeEvent_onPriceChange() {
        ItemPrice price = builders.aPrice().priceForItem(100_00, "item-id").inDb();

        //when
        Either<ErrorResult, List<ItemPrice>> result = priceFixtures.priceService.applyPercentageFactor("item-id", 10);

        //then
        assertThat(result).isRight();
        assertThatEvent(sharedFixtures.fakeEventPublisher().singleEvent())
                .isPriceChangeV1(result.get().getFirst());
    }

    @Test
    void applyPercentageFactor_updatesReadModel_onPriceChange() {
        //given
        ItemPrice price = builders.aPrice().priceForItem(100_00, "item-id").inDb();

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
                        assertThatError(err)
                                .isNotFound("No price entries found for requested item. itemId: not-existing-id"));
    }
}