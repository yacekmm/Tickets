package com.bottega.sharedlib.event.payload;

import com.bottega.sharedlib.event.EventPayload;

public record PriceChangeEventPayload(
        String priceId,
        String itemId,
        int price
        ) implements EventPayload {

}
