package com.bottega.pricing.price.tests;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import lombok.RequiredArgsConstructor;
import org.apache.groovy.util.Maps;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PriceApiClient {

    private final RequestSpecification requestSpec;

    public ValidatableResponse applyPercentageFactor(String itemId, int percentage) {
        return requestSpec
                .body(Maps.of(
                        "percentage", percentage,
                        "type", "MINUS"
                ))
                .post("/item/{itemId}/price-factor/percentage", itemId)
                .then();
    }
}
