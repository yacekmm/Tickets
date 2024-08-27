package com.bottega.promoter.fixtures;

import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.PactSpecVersion;
import com.bottega.promoter.concert.fixtures.PricingPactFixtures;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = PactFrameworkTestBase_liveCoding.PACT_PRICING_LIVE_CODING, port = "8181", pactVersion = PactSpecVersion.V3)
@SuppressWarnings("deprecation")
public class PactFrameworkTestBase_liveCoding extends FrameworkTestBase {

    public static final String PACT_PROMOTER_LIVE_CODING = "LiveCoding.Tickets.Promoter";
    public static final String PACT_PRICING_LIVE_CODING = "LiveCoding.Tickets.Pricing";

    @Autowired
    protected PricingPactFixtures pricingPactFixtures;

}
