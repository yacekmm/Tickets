package com.bottega.pricing.fixtures;

import com.bottega.sharedlib.event.*;
import com.bottega.sharedlib.event.payload.ConcertCreatedEventPayload;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.bottega.sharedlib.config.TestClockConfig.TEST_TIME_PLUS_30_DAYS;
import static com.bottega.sharedlib.event.EventType.CONCERT_CREATED;

@Component
@AllArgsConstructor
public class InitPriceChangeEventPublisher {

    private final EventPublisher eventPublisher;


    public ConcertCreatedEventPayload publishConcertCreatedEvent() {
        ConcertCreatedEventPayload payload = new ConcertCreatedEventPayload(
                UUID.randomUUID().toString(),
                "concert-title",
                TEST_TIME_PLUS_30_DAYS.toString(),
                new String[]{},
                5);

        eventPublisher.publish(Event.builder()
                .type(CONCERT_CREATED)
                .payload(payload)
                .build());

        return payload;
    }
}
