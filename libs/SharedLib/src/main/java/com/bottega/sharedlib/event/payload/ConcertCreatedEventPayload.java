package com.bottega.sharedlib.event.payload;

import com.bottega.sharedlib.event.EventPayload;

public record ConcertCreatedEventPayload (
        String concertId,
        String title,
        String date,
        String[] tags,
        int profitMarginPercentage
) implements EventPayload {
}
