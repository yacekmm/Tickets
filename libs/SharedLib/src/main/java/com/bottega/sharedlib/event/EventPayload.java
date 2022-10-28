package com.bottega.sharedlib.event;

import com.bottega.sharedlib.event.payload.ConcertCreatedEventPayload;
import com.bottega.sharedlib.event.payload.PriceChangeEventPayload;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "serialization_type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ConcertCreatedEventPayload.class, name = "CONCERT_CREATED"),
        @JsonSubTypes.Type(value = PriceChangeEventPayload.class, name = "PRICE_CHANGE")})
public interface EventPayload {
}
