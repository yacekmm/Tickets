package com.bottega.pricing.price.domain;

import com.bottega.sharedlib.event.Event;
import com.bottega.sharedlib.event.payload.PriceChangeEventPayload;

import static com.bottega.sharedlib.event.EventType.PRICE_CHANGE;

public class PricingEventFactory {
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
