package com.bottega.pricing.price.api;

import com.bottega.pricing.price.domain.ItemPrice;
import com.bottega.pricing.tests.PriceAssert;
import com.bottega.pricing.tests.PriceLogicTestBase;
import com.bottega.sharedlib.vo.error.ErrorAssert;
import com.bottega.sharedlib.vo.error.ErrorResult;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.bottega.pricing.price.api.dto.FactorErrorCode.item_not_found;
import static com.bottega.sharedlib.tests.RepoEntries.SINGULAR;
import static com.bottega.sharedlib.vo.error.ErrorType.NOT_FOUND;
import static org.assertj.vavr.api.VavrAssertions.assertThat;

class ApplyPercentageFactor_CompTest extends PriceLogicTestBase {

    @Test
    void applyPercentageFactor_returnsSingleDiscountedPrice_onValidRequest() {
        priceFixtures.priceBuilder.priceForItem(100_00, "item-id").inDb();

        //when
        Either<ErrorResult, List<ItemPrice>> result = priceFixtures.priceService.applyPercentageFactor("item-id", 10);

        //then
        assertThat(result)
                .hasRightValueSatisfying(itemPrices ->
                                PriceAssert.assertThatPrice(itemPrices.get(0))
//                                .hasFactors()
                                        .isPersistedIn(priceFixtures.priceRepo, SINGULAR)
                                        .hasPrice(90_00)
                );
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
                                .hasCode(item_not_found)
                                .hasDescription("No price entries found for requested item. itemId: not-existing-id")
                );
    }
}