package com.bottega.sharedlib.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EventType {
    PRICE_CHANGE("pricing.price"),
    CONCERT_CREATED("vendor.concert");

    private String kafkaTopic;

}
