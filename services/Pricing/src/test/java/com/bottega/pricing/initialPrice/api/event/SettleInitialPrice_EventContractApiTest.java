package com.bottega.pricing.initialPrice.api.event;

import com.bottega.pricing.fixtures.FrameworkTestBase;
import com.bottega.pricing.price.domain.ItemPrice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.contract.stubrunner.StubTrigger;

import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

class SettleInitialPrice_EventContractApiTest extends FrameworkTestBase {

    @Autowired
    StubTrigger trigger;

    @Test
    public void settleInitialPrice_createsPrice_OnContractTest() {

        //when
        trigger.trigger("triggerConcertCreatedEvent");

        //then
        await().until(() -> priceFixtures.itemPriceRepo.findAll().iterator().hasNext());

        sharedFixtures.inTransaction(() -> {

                    ItemPrice actualPrice = priceFixtures.itemPriceRepo.findAll().iterator().next();

                    //TODO: assert that price is updated with expected values and persisted in DB

                    //TODO: Assert that PRICE_CHANGED event was published, using TestEventListener
                }
        );
    }


}