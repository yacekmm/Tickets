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
public class Pricing_providesApiContract_pactApiTest extends FrameworkTestBase {

    @Value("${server.port}") int port;

    @BeforeEach
    public void setupTestTarget(PactVerificationContext context) {
        super.beforeEach();
        context.setTarget(new HttpTestTarget("localhost", port));
        System.setProperty("pact.rootDir", "build/pacts");
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    public void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @State("price for item exists")
    public void priceForItemDiscount() {
        builders.aPrice().priceForItem(100_00, "123").inDb();
    }


}