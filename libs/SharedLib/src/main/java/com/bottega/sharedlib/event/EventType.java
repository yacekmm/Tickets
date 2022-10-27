package com.bottega.sharedlib.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EventType {
    PRICE_CHANGE("pricing.priceChange"),
    CONCERT_CREATED("vendor.concertCreated");

    private String kafkaTopic;

}
