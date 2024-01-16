package com.bottega.promoter.concert.api.rest;

import java.util.HashMap;

import au.com.dius.pact.provider.*;
import au.com.dius.pact.provider.junit5.*;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import com.bottega.promoter.concert.domain.ConcertDate;
import com.bottega.promoter.concert.fixtures.clients.ConcertHttpClient;
import com.bottega.promoter.fixtures.*;
import com.bottega.sharedlib.event.Event;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.StringSerializer;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static java.time.ZoneOffset.UTC;

@Provider(PactFrameworkTestBase.PACT_PROMOTER)
@PactFolder("build/pacts")
//@PactBroker(url = "${PACT_BROKER_BASE_URL}", authentication = @PactBrokerAuth(token = "${PACT_BROKER_TOKEN}"))
public class CreateConcertRestController_createConcert_pactMessagingApiTest extends FrameworkTestBase {

    ObjectMapper objectMapper = new ObjectMapper();
    StringSerializer stringSerializer = new StringSerializer();

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void testTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @BeforeEach
    void before(PactVerificationContext context) {
        super.beforeEach();
        context.setTarget(new MessageTestTarget());
        System.setProperty("pact.verifier.publishResults", "true"); // Should only be enabled in CI.
        System.setProperty("pact.rootDir", "build/pacts");
    }

    @SneakyThrows
    @PactVerifyProvider("CONCERT_CREATED event")
    public MessageAndMetadata verifyMessageForOrder() {
        //given
        ConcertHttpClient.ConcertRequest concertRequest = ConcertHttpClient.ConcertRequest.builder()
                .title("this has to be a valid title")
                .date(ConcertDate.from("2025-12-12", sharedFixtures.clock).get().getUtcDate().atStartOfDay().toInstant(UTC))
                .build();

        //when
        concertFixtures.concertHttpClient.createConcert(concertRequest);


        return new MessageAndMetadata(
                stringSerializer.serialize("promoter.concert",
                        alignIds(sharedFixtures.testKafkaListener.singleEvent())),
                new HashMap<>());
    }

    @SneakyThrows
    private String alignIds(Event event) {
        JSONObject eventJO = new JSONObject(objectMapper.writeValueAsString(event));
        eventJO.put("id", "acb0a9a2-3f5a-43b0-bf83-053dca7b60b7");
        JSONObject payload = eventJO.getJSONObject("payload");
        payload.put("concertId", "b5365953-86f6-431f-b1b9-53fb8e8e0c0a");
        eventJO.put("payload", payload);
        return eventJO.toString();
    }



}
