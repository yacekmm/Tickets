package com.bottega.pricing.price.api.rest;

import au.com.dius.pact.provider.junit5.*;
import au.com.dius.pact.provider.junitsupport.*;
import au.com.dius.pact.provider.junitsupport.loader.*;
import com.bottega.pricing.fixtures.FrameworkTestBase;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;

@Provider("Tickets.Pricing")
@PactBroker(url = "https://jacek.pactflow.io", authentication = @PactBrokerAuth(token = "${PACT_BROKER_TOKEN}"))
@IgnoreNoPactsToVerify
public class Pricing_providesApiContract_pactApiTest extends FrameworkTestBase {

    @Value("${server.port}")
    int port;

    @BeforeEach
    public void setupTestTarget(PactVerificationContext context) {
        super.beforeEach();
        if (context != null) {
            context.setTarget(new HttpTestTarget("localhost", port));
        }
        System.setProperty("pact.rootDir", "build/pacts");
//        System.setProperty("pact.verifier.ignoreNoConsumers", "true");
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    public void pricingPactProviderVerification(PactVerificationContext context) {
        if (context != null) {
            context.verifyInteraction();
        }
    }

    @State("price for item exists")
    public void priceForItemDiscount() {
        //TODO setup system state, where contract expects price for item '123' to exist
    }


}