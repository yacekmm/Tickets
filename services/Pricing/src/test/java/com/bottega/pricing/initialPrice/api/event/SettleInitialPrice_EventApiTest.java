package com.bottega.pricing.initialPrice.api.event;

import com.bottega.pricing.fixtures.FrameworkTestBase;
import com.bottega.pricing.price.fixtures.PriceAssert;
import com.bottega.sharedlib.event.payload.ConcertCreatedEventPayload;
import org.junit.jupiter.api.Test;

import static com.bottega.sharedlib.fixtures.RepoEntries.SINGULAR;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

class SettleInitialPrice_EventApiTest extends FrameworkTestBase {

    @Test
    public void settleInitialPrice_createsNewPrice() {

        //when
        ConcertCreatedEventPayload payload = initPriceFixtures.initPriceChangeEventPublisher.publishConcertCreatedEvent();

        //then
        await().until(() -> !priceFixtures.itemPriceRepo.findByItemId(payload.concertId()).isEmpty());
        sharedFixtures.inTransaction(() ->
                PriceAssert.assertThatPrice(priceFixtures.itemPriceRepo.findByItemId(payload.concertId()).get(0))
                        .isPersistedIn(priceFixtures.itemPriceRepo, SINGULAR)
                        .hasItemId(payload.concertId())
                        .hasPrice(105_00)
                        .hasNoFactors()
        );
    }

}