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
        //TODO implement
        //given

        //when

        //then

    }

    @Test
    public void applyFactor_returns404_onItemNotFound() {
        //TODO implement
        //when

        //then

    }

}
