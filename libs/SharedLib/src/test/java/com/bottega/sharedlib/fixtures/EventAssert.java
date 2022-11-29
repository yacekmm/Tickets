package com.bottega.sharedlib.fixtures;

import com.bottega.sharedlib.event.Event;
import com.bottega.sharedlib.event.payload.ConcertCreatedEventPayload;
import com.bottega.sharedlib.event.payload.PriceChangeEventPayload;
import lombok.RequiredArgsConstructor;

import static com.bottega.sharedlib.event.EventType.CONCERT_CREATED;
import static com.bottega.sharedlib.event.EventType.PRICE_CHANGE;
import static com.bottega.sharedlib.event.EventVersion.v1;
import static lombok.AccessLevel.PRIVATE;
import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor(access = PRIVATE)
public class EventAssert {

    private final Event event;

    public static EventAssert assertThatEventV1(Event event) {
        assertThat(event.getVersion()).isEqualTo(v1);
        return new EventAssert(event);
    }

    public EventAssert isPriceChange(int expectedPrice, String expectedPriceId, String expectedItemId) {
        assertThat(event.getType()).isEqualTo(PRICE_CHANGE);
        assertThat(event.getPayload())
                .isInstanceOfSatisfying(PriceChangeEventPayload.class, payload -> {
                    assertThat(payload.price()).isEqualTo(expectedPrice);
                    assertThat(payload.priceId()).isEqualTo(expectedPriceId);
                    assertThat(payload.itemId()).isEqualTo(expectedItemId);
                });
        return this;
    }

    public EventAssert isConcertCreated(String expectedConcertId, String expectedTitle, String expectedDateTime, String[] expectedTags, int expectedMargin) {
        assertThat(event.getType()).isEqualTo(CONCERT_CREATED);
        assertThat(event.getPayload())
                .isInstanceOfSatisfying(ConcertCreatedEventPayload.class,
                        payload -> {
                            assertThat(payload.concertId()).isEqualTo(expectedConcertId);
                            assertThat(payload.concertId()).isEqualTo(expectedConcertId);
                            assertThat(payload.date()).isEqualTo(expectedDateTime);
                            assertThat(payload.title()).isEqualTo(expectedTitle);
                            assertThat(payload.title()).isEqualTo(expectedTitle);
                            assertThat(payload.tags()).containsExactlyInAnyOrder(expectedTags);
                            assertThat(payload.profitMarginPercentage()).isEqualTo(expectedMargin);
                        });
        return this;
    }
}
