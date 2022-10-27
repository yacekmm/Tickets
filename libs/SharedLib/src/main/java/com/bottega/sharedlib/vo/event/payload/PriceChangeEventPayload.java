package com.bottega.sharedlib.vo.event.payload;

import com.bottega.sharedlib.vo.event.EventPayload;

public record PriceChangeEventPayload(
        String priceId,
        String itemId,
        int price
        ) implements EventPayload {

}
