package com.bottega.vendor.concert.api.rest;

import com.bottega.vendor.concert.domain.Concert;
import com.bottega.vendor.fixtures.FrameworkTestBase;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;

import static com.bottega.vendor.config.CdcStubs.CDC_STUB_ID_PRICING;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode.LOCAL;

@AutoConfigureStubRunner(
        ids = CDC_STUB_ID_PRICING,
        stubsMode = LOCAL)
public class DiscountConcert_RestApiTest extends FrameworkTestBase {


    @Test
    public void discountConcert_discounts_onValidRequest() {
        Concert concert = concertFixtures.concertBuilder.inDb();

        //when
        ValidatableResponse response = concertFixtures.concertClient.discountConcert(concert.getId(), 10);

        //then
        response
                .statusCode(SC_OK)
                .body("$", hasSize(1))
                .body("[0].price", equalTo(90_00));
    }

}
