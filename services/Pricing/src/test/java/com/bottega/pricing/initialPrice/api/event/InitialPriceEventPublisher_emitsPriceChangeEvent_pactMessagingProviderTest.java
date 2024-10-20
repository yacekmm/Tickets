package com.bottega.pricing.initialPrice.api.event;

import au.com.dius.pact.provider.MessageAndMetadata;
import au.com.dius.pact.provider.PactVerifyProvider;
import au.com.dius.pact.provider.junit5.MessageTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.IgnoreNoPactsToVerify;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import au.com.dius.pact.provider.junitsupport.loader.PactBrokerAuth;
import com.bottega.pricing.fixtures.FrameworkTestBase;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Provider("Tickets.Pricing.Messaging")
@PactBroker(url = "${PACT_BROKER_BASE_URL}", authentication = @PactBrokerAuth(token = "${PACT_BROKER_TOKEN}"))
@IgnoreNoPactsToVerify
public class InitialPriceEventPublisher_emitsPriceChangeEvent_pactMessagingProviderTest extends FrameworkTestBase {

    //TODO: append suffix `.Messaging`  Provider and Consumer names to distinguish it from previous assignments

    //TODO: use initPriceFixtures.initPriceChangeEventPublisher.publishConcertCreatedEvent(); method to trigger Pricing service to emit PRICE_CHANGE event

}
