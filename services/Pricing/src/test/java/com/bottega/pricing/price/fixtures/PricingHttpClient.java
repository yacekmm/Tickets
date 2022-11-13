package com.bottega.pricing.price.fixtures;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import lombok.RequiredArgsConstructor;
import org.apache.groovy.util.Maps;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PricingHttpClient {

    private final RequestSpecification requestSpec;

    public ValidatableResponse applyPercentageFactor(String itemId, int percentage) {
        return requestSpec
                .body(Maps.of(
                        "percentage", percentage
                ))
                .pathParam("item-id", itemId)
                .post("/item/{item-id}/price-factor/percentage")
                .then();
    }
}
