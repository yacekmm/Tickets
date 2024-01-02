package com.bottega.promoter.fixtures;

import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.*;
import au.com.dius.pact.core.model.*;
import au.com.dius.pact.core.model.annotations.Pact;
import com.bottega.promoter.concert.fixtures.PricingPactFixtures;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = PactFrameworkTestBase.PROVIDER_PRICING, port = "8181", pactVersion = PactSpecVersion.V3)
public class PactFrameworkTestBase extends FrameworkTestBase {

    public static final String CONSUMER_PROMOTER = "Tickets.Promoter";
    public static final String PROVIDER_PRICING = "Tickets.Pricing";

    @Autowired
    protected PricingPactFixtures pricingPactFixtures;

    @Pact(consumer = CONSUMER_PROMOTER)
    public RequestResponsePact applyPercentageDiscount(PactDslWithProvider builder) {
        return pricingPactFixtures.applyPercentageDiscount(builder);
    }
}
