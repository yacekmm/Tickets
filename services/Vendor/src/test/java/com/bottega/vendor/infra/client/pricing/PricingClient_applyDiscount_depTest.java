package com.bottega.vendor.infra.client.pricing;

import com.bottega.vendor.fixtures.*;
import org.junit.jupiter.api.*;

public class PricingClient_applyDiscount_depTest extends FrameworkTestBase {

    FakePricingClient fakePricingClient;
    PricingClient realPricingClient;

    @BeforeEach
    void setUp() {
        fakePricingClient = new FakePricingClient();
        realPricingClient = concertFixtures.pricingClient;
    }

    @Test
    public void fakeApplyDiscount_isValid() {
        //given

        //when

        //then

    }

}
