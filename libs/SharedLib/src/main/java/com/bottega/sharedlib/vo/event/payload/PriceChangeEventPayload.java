package com.bottega.sharedlib.vo.event.payload;

import com.bottega.sharedlib.vo.event.EventPayload;

public record PriceChangeEventPayload(
        int price) implements EventPayload {

}
