package com.bottega.pricing.price.domain;

import com.bottega.sharedlib.vo.event.Event;
import com.bottega.sharedlib.vo.event.payload.PriceChangeEventPayload;

import static com.bottega.sharedlib.vo.event.EventType.PRICE_CHANGE;

public class EventFactory {
    public static Event priceChange(ItemPrice itemPrice) {
        return Event.builder()
                .type(PRICE_CHANGE)
                .payload(new PriceChangeEventPayload(
                        itemPrice.getId().asString(),
                        itemPrice.getItemId(),
                        itemPrice.getPrice().toInt()))
                .build();
    }
}
