package com.bottega.pricing.fixtures;

import com.bottega.pricing.price.domain.ItemPrice;
import com.bottega.sharedlib.event.Event;
import com.bottega.sharedlib.event.payload.PriceChangeEventPayload;
import lombok.RequiredArgsConstructor;
import static com.bottega.sharedlib.event.EventType.PRICE_CHANGE;
import static com.bottega.sharedlib.event.EventVersion.v1;
import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class PriceChangeEventAssert {

    private final Event event;

    public static PriceChangeEventAssert assertThatEvent(Event event) {
        return new PriceChangeEventAssert(event);
    }

    public PriceChangeEventAssert isPriceChangeV1(ItemPrice actualPrice) {
        assertThat(event.getVersion()).isEqualTo(v1);
        assertThat(event.getType()).isEqualTo(PRICE_CHANGE);
        assertThat(event.getPayload())
                .isInstanceOfSatisfying(PriceChangeEventPayload.class, payload -> {
                    assertThat(payload.price()).isEqualTo(actualPrice.getPrice().toInt());
                    assertThat(payload.priceId()).isEqualTo(actualPrice.getId().asString());
                    assertThat(payload.itemId()).isEqualTo(actualPrice.getItemId());
                });
        return this;
    }
}
