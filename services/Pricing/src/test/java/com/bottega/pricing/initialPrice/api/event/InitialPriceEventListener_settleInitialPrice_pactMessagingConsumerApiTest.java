package com.bottega.pricing.initialPrice.api.event;

import java.nio.charset.StandardCharsets;
import java.util.*;

import au.com.dius.pact.consumer.MessagePactBuilder;
import au.com.dius.pact.consumer.dsl.*;
import au.com.dius.pact.consumer.junit5.*;
import au.com.dius.pact.core.model.PactSpecVersion;
import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.core.model.messaging.*;
import com.bottega.pricing.fixtures.*;
import com.bottega.pricing.price.domain.ItemPrice;
import com.bottega.sharedlib.event.Event;
import com.bottega.sharedlib.event.payload.ConcertCreatedEventPayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Try;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.*;
import org.assertj.core.api.Assertions;
import org.json.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import static com.bottega.pricing.price.fixtures.PriceAssert.assertThatPrice;
import static com.bottega.sharedlib.event.EventType.CONCERT_CREATED;
import static com.bottega.sharedlib.fixtures.RepoEntries.SINGULAR;
import static com.toomuchcoding.jsonassert.JsonAssertion.assertThat;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "Tickets.Promoter", providerType = ProviderType.ASYNCH, pactVersion = PactSpecVersion.V3)
class InitialPriceEventListener_settleInitialPrice_pactMessagingConsumerApiTest extends FrameworkTestBase {

    @Autowired
    ObjectMapper objectMapper;
    StringSerializer stringSerializer = new StringSerializer();
    StringDeserializer stringDeserializer = new StringDeserializer();

    @BeforeEach
    void setUp() {
        super.beforeEach();
        System.setProperty("pact.rootDir", "build/pacts");
    }

    @Pact(consumer = "Tickets.Pricing")
    @SneakyThrows
    MessagePact concertCreatedPact(MessagePactBuilder builder) {

        Event event = Event.builder()
                .type(CONCERT_CREATED)
                .payload(new ConcertCreatedEventPayload(
                        "concert-id",
                        "this has to be a valid title",
                        "2025-12-12",
                        new String[]{},
                        5))
                .build();


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
//                .withContent(new String(stringSerializer.serialize("promoter.concert", objectMapper.writeValueAsString(event)), StandardCharsets.UTF_8))
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