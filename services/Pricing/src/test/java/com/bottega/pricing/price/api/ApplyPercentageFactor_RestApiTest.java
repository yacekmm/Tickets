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

        //TODO: assert that returned price with value and factors
        //then
//        PriceAssert
//                .assertThatPrice(concertFixtures.concertRepo.findAll().iterator().next())
////        //TODO: concert Properties in DB: Dependency test
//                .isPersistedIn(concertFixtures.concertRepo, RepoEntries.SINGULAR)
//                .hasIdAsUUID()
//                .hasTitle("concert-title")
//                .hasDateTime(TEST_TIME_PLUS_30_DAYS)
//                .hasVendorId("some-id")
//                .extractId();
//
//
//        //TODO: API response is valid: API test
        response
                .statusCode(SC_OK)
                .body("prices", hasSize(1))
                .body("prices[0].priceId", equalTo(itemPrice.getId().asString()))
                .body("prices[0].itemId", equalTo(itemPrice.getItemId()))
                .body("prices[0].price", equalTo(90_00))
                .body("prices[0].factors", hasSize(1))
                .body("prices[0].factors[0].type", equalTo("PERCENTAGE"))
                .body("prices[0].factors[0].value", equalTo(10));
    }

}
