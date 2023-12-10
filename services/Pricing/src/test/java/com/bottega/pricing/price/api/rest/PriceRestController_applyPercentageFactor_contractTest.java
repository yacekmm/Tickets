package com.bottega.pricing.price.api.rest;

import au.com.dius.pact.provider.junit5.*;
import au.com.dius.pact.provider.junitsupport.*;
import au.com.dius.pact.provider.junitsupport.loader.*;
import com.bottega.pricing.fixtures.FrameworkTestBase;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;

@Provider("Tickets.Pricing")
@PactBroker(scheme = "https", host = "jacek.pactflow.io", authentication = @PactBrokerAuth(token = "_3OueOshqrE-c5B-5LIu1g"))
public class PriceRestController_applyPercentageFactor_contractTest extends FrameworkTestBase {

    @Value("${server.port}") int port;

    @BeforeEach
    public void setupTestTarget(PactVerificationContext context) {
        super.beforeEach();
        context.setTarget(new HttpTestTarget("localhost", port));
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    public void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @State("price for item exists")
    public void priceForItemDiscount() {
        System.out.println("a price for item 123 exists");
        builders.aPrice().priceForItem(100_00, "123").inDb();
    }


}