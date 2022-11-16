package com.bottega.vendor.infra;

import com.bottega.sharedlib.event.Event;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@Component
@RequiredArgsConstructor
@Slf4j
public class TestKafkaEventListener {

    private final ObjectMapper objectMapper;
    private List<Event> receivedEvents = new ArrayList<>();

    @KafkaListener(id = "test-concert-listener", topics = "vendor.concert")
    public void listen(Message<String> message) {

        Try.of(() -> objectMapper.readValue(message.getPayload(), Event.class))
                .onFailure(throwable -> log.info("Error Parsing kafka message: {}, ex: {}", message, throwable))
                .peek(event -> receivedEvents.add(event));
    }


    public void tearDown() {
        receivedEvents = new ArrayList<>();
    }

    public Event singleEvent() {
        assertThat(receivedEvents).hasSize(1);
        return receivedEvents.get(0);
    }
}
