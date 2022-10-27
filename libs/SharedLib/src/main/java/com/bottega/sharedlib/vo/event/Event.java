package com.bottega.sharedlib.vo.event;

import lombok.Builder;
import lombok.Getter;

import static com.bottega.sharedlib.vo.event.EventVersion.v1;

@Builder
@Getter
public class Event {

    @Builder.Default
    private final EventVersion version = v1;
    private final EventType type;
    private final EventPayload payload;
}
