package com.bottega.promoter.concertRead;

import au.com.dius.pact.consumer.MessagePactBuilder;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.consumer.junit5.ProviderType;
import au.com.dius.pact.core.model.PactSpecVersion;
import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.core.model.messaging.Message;
import au.com.dius.pact.core.model.messaging.MessagePact;
import com.bottega.promoter.fixtures.FrameworkTestBase;
import com.bottega.sharedlib.event.Event;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Try;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "Tickets.Pricing.Messaging", providerType = ProviderType.ASYNCH, pactVersion = PactSpecVersion.V3)
class PriceChangeEventListener_updatesReadModel_pactMessagingConsumerApiTest extends FrameworkTestBase {

    @Autowired
    ObjectMapper objectMapper;
    StringDeserializer stringDeserializer = new StringDeserializer();

    @BeforeEach
    void setUp() {
        super.beforeEach();
        System.setProperty("pact.rootDir", "build/pacts");
    }

    @Pact(consumer = "Tickets.Promoter.Messaging")
    @SneakyThrows
    MessagePact priceChangedPact(MessagePactBuilder builder) {

        DslPart json = new PactDslJsonBody()
                .uuid("id")
                .stringType("type", "PRICE_CHANGE")
                .object("payload")
                .stringType("serialization_type", "PRICE_CHANGE")
                .uuid("priceId")
                .uuid("itemId")
                .integerType("price", 100_00)
                .closeObject();

        return builder.expectsToReceive("PRICE_CHANGE event")
                .withContent(json)
                .toPact();
    }

    @Test
    @SneakyThrows
    @PactTestFor(pactMethod = "priceChangedPact", providerType = ProviderType.ASYNCH)
    public void settleInitialPrice_createsPrice_onConcertCreatedEvent(List<Message> messages) {
        //when
        triggerEvents(messages);

        //then
        await().until(() -> concertReadFixtures.concertPriceRepo.findAll().iterator().hasNext());
        ConcertPrice concertPrice = concertReadFixtures.concertPriceRepo.findAll().iterator().next();
        Assertions.assertThat(concertPrice.getPrice()).isEqualTo(100_00);
    }

    @SneakyThrows
    private void triggerEvents(List<Message> messages) {
        messages.stream()
                .map(Message::contentsAsBytes)
                .map(bytes -> stringDeserializer.deserialize("pricing.price", bytes))
                .map(deserialized -> Try.of(() -> objectMapper.readValue(deserialized, Event.class)))
                .map(Try::get)
                .forEach(sharedFixtures.eventPublisher::publish);
    }


}