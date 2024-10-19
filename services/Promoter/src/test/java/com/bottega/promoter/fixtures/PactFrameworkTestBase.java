package com.bottega.promoter.fixtures;

import com.bottega.promoter.concert.fixtures.PricingPactFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("deprecation")
public class PactFrameworkTestBase extends FrameworkTestBase {

    public static final String PACT_PROMOTER = "Tickets.Promoter";
    public static final String PACT_PRICING = "Tickets.Pricing";

    @Autowired
    protected PricingPactFixtures pricingPactFixtures;

    @BeforeEach
    void setUp() {
        super.beforeEach();
        System.setProperty("pact.rootDir", "build/pacts");
    }

}
