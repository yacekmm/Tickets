package com.bottega.pricing.price.api.rest;

import com.bottega.pricing.fixtures.*;
import com.bottega.pricing.price.domain.ItemPrice;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.apache.groovy.util.Maps;
import org.junit.jupiter.api.Test;

import static io.restassured.http.ContentType.JSON;

public class PriceRestController_ApplyPercentageFactor_RestApiTest extends FrameworkTestBase {

    @Test
    public void applyFactor_OK_onValidRequest() {
        //given
        String itemId = "item-id";
        ItemPrice itemPrice = builders.aPrice().priceForItem(100_00, itemId).inDb();

        //when
        RestAssured.port = 8181;
        ValidatableResponse response = RestAssured.given()
                .contentType(JSON)
                .body(Maps.of(
                        "percentage", 20
                ))
                .pathParam("item-id", itemId)
                .post("api/v1/item/{item-id}/price-factor/percentage")
                .then();

        //then
        PriceJsonAssert.assertThatPrice(response)
                .hasSinglePrice(itemPrice, 80_00)
                .hasSinglePercentageFactor(20);
    }

    @Test
    public void applyFactor_returns404_onItemNotFound() {
        //when


        //then

    }

}
