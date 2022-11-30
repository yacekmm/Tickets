package com.bottega.pricing.price.api.app;

import com.bottega.pricing.fixtures.LogicTestBase;
import com.bottega.pricing.price.domain.ItemPrice;
import com.bottega.pricing.price.fixtures.PriceAssert;
import com.bottega.sharedlib.fixtures.EventAssert;
import com.bottega.sharedlib.vo.Money;
import com.bottega.sharedlib.vo.error.ErrorResult;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;
import static com.bottega.sharedlib.fixtures.RepoEntries.SINGULAR;
import static org.assertj.vavr.api.VavrAssertions.assertThat;

class PriceService_addNewPrice_compTest extends LogicTestBase {

    @Test
    void addNewPrice_createsPrice_onValidRequest() {

        //when
        Either<ErrorResult, ItemPrice> result = priceFixtures.priceService.addNewPrice("item-id", new Money(10_00));

        //then
        assertThat(result).hasRightValueSatisfying(itemPrice ->
                PriceAssert.assertThatPrice(itemPrice)
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
                EventAssert.assertThatEventV1(sharedFixtures.fakeEventPublisher().singleEvent())
                        .isPriceChange(itemPrice.getPrice().toInt(), itemPrice.getId().asString(), itemPrice.getItemId()));

    }


}