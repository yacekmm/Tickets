package com.bottega.pricing.fixtures;

import com.bottega.pricing.price.domain.ItemPrice;
import io.restassured.response.ValidatableResponse;
import lombok.RequiredArgsConstructor;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.*;

@RequiredArgsConstructor(staticName = "assertThatPrice")
public class PriceJsonAssert {

    private final ValidatableResponse response;

    public PriceJsonAssert hasSinglePrice(ItemPrice expectedPriceId, int priceValue) {
        response
                .statusCode(SC_OK)
                .body("$", hasSize(1))
                .body("[0].priceId", equalTo(expectedPriceId.getId().asString()))
                .body("[0].itemId", equalTo(expectedPriceId.getItemId()))
                .body("[0].price", equalTo(priceValue));
        return this;
    }

    public PriceJsonAssert hasSinglePercentageFactor(int value) {
        response
                .statusCode(SC_OK)
                .body("[0].factors", hasSize(1))
                .body("[0].factors[0].type", equalTo("PERCENTAGE"))
                .body("[0].factors[0].value", equalTo(value));
        return this;
    }
}
