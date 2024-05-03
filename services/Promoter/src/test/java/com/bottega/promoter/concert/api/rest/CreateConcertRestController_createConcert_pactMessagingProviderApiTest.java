package com.bottega.promoter.concert.api.rest;

import java.time.LocalDate;
import java.util.*;

import au.com.dius.pact.provider.*;
import au.com.dius.pact.provider.junit5.*;
import au.com.dius.pact.provider.junitsupport.*;
import au.com.dius.pact.provider.junitsupport.loader.*;
import com.bottega.promoter.concert.fixtures.clients.ConcertHttpClient;
import com.bottega.promoter.fixtures.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import static java.time.ZoneOffset.UTC;

@Provider(PactFrameworkTestBase.PACT_PROMOTER)
@PactBroker(url = "${PACT_BROKER_BASE_URL}", authentication = @PactBrokerAuth(token = "${PACT_BROKER_TOKEN}"))
@IgnoreNoPactsToVerify
public class CreateConcertRestController_createConcert_pactMessagingProviderApiTest extends FrameworkTestBase {

    @Autowired
    ObjectMapper objectMapper;

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void testTemplate(PactVerificationContext context) {
        if(context!= null) {
            context.verifyInteraction();
        }
    }

    @BeforeEach
    void before(PactVerificationContext context) {
        super.beforeEach();
        if(context!= null) {
            context.setTarget(new MessageTestTarget(List.of("com.bottega")));
        }
        System.setProperty("pact.verifier.publishResults", "false"); // Should only be enabled in CI.
        System.setProperty("pact.rootDir", "build/pacts");
    }

    @SneakyThrows
    @PactVerifyProvider("CONCERT_CREATED event")
    MessageAndMetadata createConcert_emitsConcertCreatedPactEvent() {
        //given
        ConcertHttpClient.ConcertRequest concertRequest = ConcertHttpClient.ConcertRequest.builder()
                .title("this has to be a valid title")
                .date(LocalDate.of(2025, 12, 12).atStartOfDay().toInstant(UTC))
                .build();

        //when
        concertFixtures.concertHttpClient.createConcert(concertRequest);

        return new MessageAndMetadata(
                objectMapper.writeValueAsBytes(sharedFixtures.testKafkaListener.singleEvent()),
                Map.of("contentType", "application/json"));
    }

}
