package com.bottega.pricing.price.fixtures;

import com.bottega.pricing.fixtures.TestBuilders;
import io.restassured.response.ValidatableResponse;
import lombok.RequiredArgsConstructor;
import org.apache.groovy.util.Maps;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PricingHttpClient {

    private final TestBuilders builders;

    public ValidatableResponse notImportant(String itemId, int percentage) {
        return builders.aRequestSpec()
                .body(Maps.of(
                        "percentage", percentage
                ))
                .pathParam("item-id", itemId)
                .post("/item/{item-id}/price-factor/percentage")
                .then();
    }
}
