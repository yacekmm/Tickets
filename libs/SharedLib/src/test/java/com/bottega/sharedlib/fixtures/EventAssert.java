package com.bottega.sharedlib.fixtures;

import com.bottega.sharedlib.vo.event.Event;
import com.bottega.sharedlib.vo.event.payload.PriceChangeEventPayload;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.InstanceOfAssertFactories;

import static com.bottega.sharedlib.vo.event.EventType.PRICE_CHANGE;
import static com.bottega.sharedlib.vo.event.EventVersion.v1;
import static lombok.AccessLevel.PRIVATE;
import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor(access = PRIVATE)
public class EventAssert {

    private final Event event;

    public static EventAssert assertThatEventV1(Event event){
        assertThat(event.getVersion()).isEqualTo(v1);
        return new EventAssert(event);
    }

    public void isPriceChangeWithValue(int expectedPrice, String expectedPriceId, String expectedItemId) {
        assertThat(event.getType()).isEqualTo(PRICE_CHANGE);
        assertThat(event.getPayload())
                .asInstanceOf(InstanceOfAssertFactories.type(PriceChangeEventPayload.class))
                .matches(payload -> payload.price() == expectedPrice)
                .matches(payload -> payload.priceId().equals(expectedPriceId))
                .matches(payload -> payload.itemId().equals(expectedItemId));

    }
}
