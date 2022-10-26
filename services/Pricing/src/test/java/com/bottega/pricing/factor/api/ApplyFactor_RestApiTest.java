package com.bottega.pricing.factor.api;

import com.bottega.pricing.tests.FrameworkTestBase;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import static org.apache.http.HttpStatus.SC_OK;

public class ApplyFactor_RestApiTest extends FrameworkTestBase {


    @Test
    public void applyFactor_applies_onValidRequest() {

        //when
        ValidatableResponse response = factorFixtures.factorClient.applyPercentageFactor("item-id", 10);

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
                .statusCode(SC_OK);
    }

}
