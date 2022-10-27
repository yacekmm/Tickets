package com.bottega.sharedlib.vo.event;

import com.bottega.sharedlib.vo.Money;
import com.bottega.sharedlib.vo.event.payload.PriceChangeEventPayload;

import static com.bottega.sharedlib.vo.event.EventType.PRICE_CHANGE;
import static com.bottega.sharedlib.vo.event.EventVersion.v1;

public class EventFactory {
    public static Event priceChange(Money price) {
        return new Event(v1, PRICE_CHANGE, new PriceChangeEventPayload(price.toInt()));
    }
}
