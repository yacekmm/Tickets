package com.bottega.promoter.concert.fixtures;

import com.bottega.promoter.concert.domain.*;
import com.bottega.sharedlib.event.Event;
import com.bottega.sharedlib.event.payload.ConcertCreatedEventPayload;
import lombok.RequiredArgsConstructor;
import static com.bottega.sharedlib.event.EventType.CONCERT_CREATED;
import static com.bottega.sharedlib.event.EventVersion.v1;
import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class ConcertCreatedEventAssert {

    private final Event event;

    public static ConcertCreatedEventAssert assertThatEvent(Event event) {
        return new ConcertCreatedEventAssert(event);
    }

    public ConcertCreatedEventAssert isConcertCreatedV1(Concert expectedConcert, int expectedMargin) {
        assertThat(event.getVersion()).isEqualTo(v1);
        assertThat(event.getType()).isEqualTo(CONCERT_CREATED);
        assertThat(event.getPayload())
                .isInstanceOfSatisfying(ConcertCreatedEventPayload.class,
                        payload -> {
                            assertThat(payload.concertId()).isEqualTo(expectedConcert.getId().asString());
                            assertThat(payload.date()).isEqualTo(expectedConcert.getDate().getUtcDate().toString());
                            assertThat(payload.title()).isEqualTo(expectedConcert.getTitle().getValue());
                            assertThat(payload.tags()).containsExactlyInAnyOrder(expectedConcert.getTags().stream().map(Tag::getValue).toList().toArray(String[]::new));
                            assertThat(payload.profitMarginPercentage()).isEqualTo(expectedMargin);
                        });
        return this;
    }
}
