package com.bottega.pricing.price.api.app;

import com.bottega.pricing.fixtures.LogicTestBase;
import com.bottega.pricing.price.domain.ItemPrice;
import com.bottega.sharedlib.vo.Money;
import com.bottega.sharedlib.vo.error.ErrorResult;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;
import static com.bottega.pricing.fixtures.PriceChangeEventAssert.assertThatEvent;
import static com.bottega.pricing.price.fixtures.PriceAssert.assertThatPrice;
import static com.bottega.sharedlib.fixtures.RepoEntries.SINGULAR;
import static org.assertj.vavr.api.VavrAssertions.assertThat;

class PriceService_addNewPrice_compTest extends LogicTestBase {

    @Test
    void addNewPrice_createsPrice_onValidRequest() {

        //when
        Either<ErrorResult, ItemPrice> result = priceFixtures.priceService.addNewPrice("item-id", new Money(10_00));

        //then
        assertThat(result).hasRightValueSatisfying(itemPrice ->
                assertThatPrice(itemPrice)
                        .isPersistedIn(priceFixtures.itemPriceRepo, SINGULAR)
                        .hasPrice(10_00)
                        .hasItemId("item-id")
                        .hasNoFactors()
        );
    }

    @Test
    void addNewPrice_publishesPriceChangeEvent_onValidRequest() {

        //when
        Either<ErrorResult, ItemPrice> result = priceFixtures.priceService.addNewPrice("item-id", new Money(10_00));

        //then
        assertThat(result).hasRightValueSatisfying(itemPrice ->
                assertThatEvent(sharedFixtures.fakeEventPublisher().singleEvent())
                        .isPriceChangeV1(itemPrice));

    }


}