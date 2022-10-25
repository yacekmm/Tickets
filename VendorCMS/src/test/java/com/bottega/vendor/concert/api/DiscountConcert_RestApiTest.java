package com.bottega.vendor.concert.api;

import com.bottega.vendor.concert.domain.Concert;
import com.bottega.vendor.tests.FrameworkTestBase;
import io.restassured.response.ValidatableResponse;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerRule;

import static org.apache.http.HttpStatus.SC_ACCEPTED;

//@AutoConfigureStubRunner(
//        ids = "com.bottega:Pricing:1.0.0-SNAPSHOT:stubs:8181",
//        stubsMode = StubRunnerProperties.StubsMode.LOCAL
//)
public class DiscountConcert_RestApiTest extends FrameworkTestBase {


    @ClassRule
    public static StubRunnerRule rule = new StubRunnerRule()
            .downloadStub("com.bottega:Pricing:1.0.0-SNAPSHOT:stubs:8181");

    @Test
    public void discountConcert_discounts_onValidRequest() {
        Concert concert = concertFixtures.concertBuilder.inDb();

        //when
        ValidatableResponse response = concertFixtures.concertClient.discountConcert(concert.getId(), 10);

        //then
        response.statusCode(SC_ACCEPTED);
    }

}
