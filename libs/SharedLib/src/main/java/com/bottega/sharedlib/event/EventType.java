package com.bottega.sharedlib.event;

import lombok.*;

@AllArgsConstructor
@Getter
public enum EventType {
    PRICE_CHANGE("pricing.price"),
    CONCERT_CREATED("promoter.concert");

    private String kafkaTopic;

}
