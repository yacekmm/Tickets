package com.bottega.promoter.fixtures;

import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.PactSpecVersion;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.bottega.promoter.concert.fixtures.PricingPactFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = PactFrameworkTestBase.PACT_PRICING, port = "8181", pactVersion = PactSpecVersion.V3)
@SuppressWarnings("deprecation")
public class PactFrameworkTestBase extends FrameworkTestBase {

    public static final String PACT_PROMOTER = "Tickets.Promoter";
    public static final String PACT_PROMOTER_MESSAGING = "Tickets.Promoter.Messaging";
    public static final String PACT_PRICING = "Tickets.Pricing";
    public static final String PACT_PRICING_MESSAGING = "Tickets.Pricing.Messaging";

    @Autowired
    protected PricingPactFixtures pricingPactFixtures;

    @BeforeEach
    void setUp() {
        super.beforeEach();
        System.setProperty("pact.rootDir", "build/pacts");
    }

    @Pact(consumer = PACT_PROMOTER)
    public RequestResponsePact applyPercentageDiscount(PactDslWithProvider builder) {
        return pricingPactFixtures.applyPercentageDiscount(builder);
    }
}
