package com.bottega.sharedlib.vo.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EventType {
    PRICE_CHANGE("pricing.priceChange");

    private String kafkaTopic;

}
