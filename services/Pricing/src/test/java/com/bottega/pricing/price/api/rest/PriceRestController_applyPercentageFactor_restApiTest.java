package com.bottega.pricing.price.api.rest;

import com.bottega.pricing.fixtures.*;
import com.bottega.pricing.price.domain.ItemPrice;
import com.bottega.sharedlib.fixtures.ErrorJsonAssert;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;
import static com.bottega.pricing.fixtures.PriceJsonAssert.assertThatPrice;
import static com.bottega.sharedlib.fixtures.ErrorJsonAssert.assertThatError;

public class PriceRestController_applyPercentageFactor_restApiTest extends FrameworkTestBase {

    @Test
    public void applyFactor_OK_onValidRequest() {
        //given
        ItemPrice itemPrice = builders.aPrice().priceForItem(100_00, "item-id").inDb();

        //when
        ValidatableResponse response = priceFixtures.pricingHttpClient.applyPercentageFactor("item-id", 20);

        //then
        assertThatPrice(response)
                .hasSinglePrice(itemPrice, 80_00)
                .hasSinglePercentageFactor(20);
    }

    @Test
    public void applyFactor_returns404_onItemNotFound() {
        //when
        ValidatableResponse response = priceFixtures.pricingHttpClient.applyPercentageFactor("non-existing-item-id", 10);

        //then
        assertThatError(response)
                .isNotFound("No price entries found for requested item. itemId: non-existing-item-id");
    }

}
