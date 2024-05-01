package com.bottega.sharedlib.event;

import java.util.UUID;

import lombok.*;
import static com.bottega.sharedlib.event.EventVersion.v1;

@Builder
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Builder.Default
    private UUID id = UUID.randomUUID();
    @Builder.Default
    private EventVersion version = v1;
    private EventType type;
    private EventPayload payload;
}
