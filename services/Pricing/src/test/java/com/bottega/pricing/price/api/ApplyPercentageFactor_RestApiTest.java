package com.bottega.pricing.price.api;

import com.bottega.pricing.price.domain.ItemPrice;
import com.bottega.pricing.tests.FrameworkTestBase;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class ApplyPercentageFactor_RestApiTest extends FrameworkTestBase {

    //TODO/JM: implement publishing an event

    @Test
    public void applyFactor_applies_onValidRequest() {
        //given
        ItemPrice itemPrice = priceFixtures.priceBuilder.priceForItem(100_00, "item-id").inDb();

        //when
        ValidatableResponse response = factorFixtures.factorClient.applyPercentageFactor("item-id", 10);

        //then
        response
                .statusCode(SC_OK)
                .body("$", hasSize(1))
                .body("[0].priceId", equalTo(itemPrice.getId().asString()))
                .body("[0].itemId", equalTo(itemPrice.getItemId()))
                .body("[0].price", equalTo(90_00))
                .body("[0].factors", hasSize(1))
                .body("[0].factors[0].type", equalTo("PERCENTAGE"))
                .body("[0].factors[0].value", equalTo(10));
    }

}
