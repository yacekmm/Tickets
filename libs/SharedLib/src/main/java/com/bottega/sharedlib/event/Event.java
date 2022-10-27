package com.bottega.sharedlib.event;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

import static com.bottega.sharedlib.event.EventVersion.v1;

@Builder
@Getter
@ToString
public class Event {

    @Builder.Default
    private final UUID id = UUID.randomUUID();
    @Builder.Default
    private final EventVersion version = v1;
    private final EventType type;
    private final EventPayload payload;
}
