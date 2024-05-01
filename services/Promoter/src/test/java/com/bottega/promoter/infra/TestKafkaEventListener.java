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
        Awaitility.await().until(() -> receivedEvents.size() == 1);
        return receivedEvents.getFirst();
    }
}
