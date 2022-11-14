package com.bottega.pricing.initialPrice.api.event;

import com.bottega.pricing.initialPrice.InitialPriceService;
import com.bottega.sharedlib.event.Event;
import com.bottega.sharedlib.event.payload.ConcertCreatedEventPayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static java.util.stream.Collectors.toSet;

@Component
@AllArgsConstructor
@Slf4j
public class InitialPriceEventListener {

    private final InitialPriceService initialPriceService;
    private final ObjectMapper objectMapper;


    @KafkaListener(id = "concert-listener", topics = "vendor.concert")
    public void listen(Message<String> message) {

        Try.of(() -> objectMapper.readValue(message.getPayload(), Event.class))
                .onFailure(throwable -> log.info("Error Parsing kafka message: {}, ex: {}", message, throwable))
                .peek(this::broker);
    }

    public void broker(Event event) {
        if (event.getPayload() instanceof ConcertCreatedEventPayload payload) {
            initialPriceService.settleInitialPrice(payload.concertId(), payload.profitMarginPercentage(), Arrays.stream(payload.tags()).collect(toSet()));
        } else {
            log.info("Ignoring event: unsupported payload type: {}", event.getPayload());
        }
    }
}
