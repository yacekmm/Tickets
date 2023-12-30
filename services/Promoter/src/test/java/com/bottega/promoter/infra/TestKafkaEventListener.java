package com.bottega.promoter.infra;

import java.util.*;

import com.bottega.sharedlib.event.Event;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.testcontainers.shaded.org.awaitility.Awaitility;
import static org.assertj.core.api.Assertions.assertThat;

@Component
@RequiredArgsConstructor
@Slf4j
public class TestKafkaEventListener {

    public static final String TOPIC = "promoter.concert";
    private final ObjectMapper objectMapper;
    private List<Event> receivedEvents = new ArrayList<>();

    @KafkaListener(id = "test-concert-listener", topics = TOPIC)
    public void listen(Message<String> message) {

        Try.of(() -> objectMapper.readValue(message.getPayload(), Event.class))
                .onFailure(throwable -> log.info("Error Parsing kafka message: {}, ex: {}", message, throwable))
                .peek(event -> receivedEvents.add(event));
    }


    public void tearDown() {
        receivedEvents = new ArrayList<>();
    }

    public Event singleEvent() {
        //TODO tests are unstable as kafka listener is not always ready
        assertThat(receivedEvents).hasSize(1);
        return receivedEvents.getFirst();
    }
}
