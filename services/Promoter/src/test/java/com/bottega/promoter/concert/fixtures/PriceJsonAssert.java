package com.bottega.promoter.concert.fixtures;

import io.restassured.response.ValidatableResponse;
import lombok.RequiredArgsConstructor;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@RequiredArgsConstructor
public class PriceJsonAssert {

    private final ValidatableResponse response;

    public static PriceJsonAssert assertThatPrice(ValidatableResponse response) {
        return new PriceJsonAssert(response);
    }

    public PriceJsonAssert hasSinglePrice(int priceValue) {
        response
                .statusCode(SC_OK)
                .body("$", hasSize(1))
                .body("[0].price", equalTo(priceValue));
        return this;
    }
}
