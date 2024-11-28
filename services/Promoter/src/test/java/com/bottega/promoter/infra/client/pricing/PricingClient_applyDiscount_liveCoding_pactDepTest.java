package com.bottega.promoter.infra.client.pricing;

import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.bottega.promoter.concert.domain.ConcertId;
import com.bottega.promoter.fixtures.FakePricingClient;
import com.bottega.promoter.fixtures.PactFrameworkTestBase_liveCoding;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.bottega.promoter.concert.fixtures.asserts.PriceAssert.assertThatPrice;

public class PricingClient_applyDiscount_liveCoding_pactDepTest extends PactFrameworkTestBase_liveCoding {

    FakePricingClient fakePricingClient;
    PricingClient httpPricingClient;


    @BeforeEach
    void setUp() {
        fakePricingClient = new FakePricingClient();
        httpPricingClient = concertFixtures.pricingClient;
    }

    @Pact(consumer = PACT_PROMOTER_LIVE_CODING)
    @SuppressWarnings("unused")
    public RequestResponsePact stubApplyPercentageDiscount(PactDslWithProvider builder) {
        return pricingStubs.stubApplyPercentageDiscount(builder);
    }

    @Test
    public void applyDiscount_isValid() {

        //when
        var result = httpPricingClient.applyPercentageDiscount(new ConcertId("123"), 10);

        //then
        assertThatPrice(result.get().getFirst())
                .equalTo(90_00);
    }

}
