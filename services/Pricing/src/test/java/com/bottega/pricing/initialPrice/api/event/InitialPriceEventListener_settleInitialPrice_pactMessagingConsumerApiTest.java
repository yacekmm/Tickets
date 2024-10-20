package com.bottega.pricing.initialPrice.api.event;

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
import com.bottega.pricing.fixtures.FrameworkTestBase;
import com.bottega.pricing.fixtures.PriceChangeEventAssert;
import com.bottega.pricing.price.domain.ItemPrice;
import com.bottega.sharedlib.event.Event;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Try;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.bottega.pricing.price.fixtures.PriceAssert.assertThatPrice;
import static com.bottega.sharedlib.fixtures.RepoEntries.SINGULAR;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "Tickets.Promoter", providerType = ProviderType.ASYNCH, pactVersion = PactSpecVersion.V3)
class InitialPriceEventListener_settleInitialPrice_pactMessagingConsumerApiTest extends FrameworkTestBase {

    @Autowired
    ObjectMapper objectMapper;
    StringDeserializer stringDeserializer = new StringDeserializer();

    @BeforeEach
    void setUp() {
        super.beforeEach();
        System.setProperty("pact.rootDir", "build/pacts");
    }

    @Pact(consumer = "Tickets.Pricing")
    @SneakyThrows
    MessagePact concertCreatedPact(MessagePactBuilder builder) {

        DslPart json = new PactDslJsonBody()
                .uuid("id")
                .stringType("type", "CONCERT_CREATED")
                .object("payload")
                .stringType("serialization_type", "CONCERT_CREATED")
                .uuid("concertId")
                .stringType("title", "this has to be a valid title")
                .stringType("date", "2025-12-12")
                .integerType("profitMarginPercentage", 5)
                .array("tags")
                .closeArray()
                .closeObject();

        return builder.expectsToReceive("CONCERT_CREATED event")
                .withContent(json)
                .toPact();
    }

    @Test
    @SneakyThrows
    @PactTestFor(pactMethod = "concertCreatedPact", providerType = ProviderType.ASYNCH)
    public void settleInitialPrice_createsPrice_onConcertCreatedEvent(List<Message> messages) {
        //when
        triggerEvents(messages);

        //then
        await().until(() -> priceFixtures.itemPriceRepo.findAll().iterator().hasNext());

        ItemPrice actualPrice = priceFixtures.itemPriceRepo.findAll().iterator().next();

        assertThatPrice(actualPrice)
                .isPersistedIn(priceFixtures.itemPriceRepo, SINGULAR)
                .hasPrice(105_00)
                .hasNoFactors();

        PriceChangeEventAssert.assertThatEvent(sharedFixtures.testEventListener.singleEvent())
                .isPriceChangeV1(actualPrice);
    }

    @SneakyThrows
    private void triggerEvents(List<Message> messages) {
        messages.stream()
                .map(Message::contentsAsBytes)
                .map(bytes -> stringDeserializer.deserialize("promoter.concert", bytes))
                .map(deserialized -> Try.of(() -> objectMapper.readValue(deserialized, Event.class)))
                .map(Try::get)
                .forEach(sharedFixtures.eventPublisher::publish);
    }


}