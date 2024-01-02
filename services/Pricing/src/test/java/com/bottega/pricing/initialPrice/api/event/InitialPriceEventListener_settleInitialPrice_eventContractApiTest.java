package com.bottega.pricing.initialPrice.api.event;

import com.bottega.pricing.fixtures.*;
import com.bottega.pricing.price.domain.ItemPrice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.contract.stubrunner.StubTrigger;
import static com.bottega.pricing.price.fixtures.PriceAssert.assertThatPrice;
import static com.bottega.sharedlib.fixtures.RepoEntries.SINGULAR;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

class InitialPriceEventListener_settleInitialPrice_eventContractApiTest extends FrameworkTestBase {

    @Autowired
    StubTrigger trigger;

    @Test
    public void settleInitialPrice_createsPrice_OnContractTest() {

        //when
        trigger.trigger("triggerConcertCreatedEvent");

        //then
        await().until(() -> priceFixtures.itemPriceRepo.findAll().iterator().hasNext());

        ItemPrice actualPrice = priceFixtures.itemPriceRepo.findAll().iterator().next();

        assertThatPrice(actualPrice)
                .isPersistedIn(priceFixtures.itemPriceRepo, SINGULAR)
                .hasPrice(105_00)
                .hasNoFactors();

        //TODO assert that PriceChange event is published with PriceChangeEventAssert
    }


}