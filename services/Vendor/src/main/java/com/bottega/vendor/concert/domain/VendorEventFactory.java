package com.bottega.vendor.concert.domain;

import com.bottega.sharedlib.event.Event;
import com.bottega.sharedlib.event.payload.ConcertCreatedEventPayload;

import static com.bottega.sharedlib.event.EventType.CONCERT_CREATED;

public class VendorEventFactory {
    public static Event concertCreated(Concert concert, int profitMarginPercentage) {
        return Event.builder()
                .type(CONCERT_CREATED)
                .payload(new ConcertCreatedEventPayload(
                        concert.getId().asString(),
                        concert.getTitle().getValue(),
                        concert.getDate().getUtcDate().toString(),
                        new String[]{},
                        profitMarginPercentage))
                .build();
    }
}
