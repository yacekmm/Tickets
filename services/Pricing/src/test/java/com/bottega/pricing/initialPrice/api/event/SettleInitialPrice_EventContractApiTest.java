package com.bottega.pricing.initialPrice.api.event;

import com.bottega.pricing.fixtures.FrameworkTestBase;
import com.bottega.pricing.price.domain.ItemPrice;
import com.bottega.pricing.price.fixtures.PriceAssert;
import com.bottega.sharedlib.fixtures.EventAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.contract.stubrunner.StubTrigger;

import static com.bottega.sharedlib.fixtures.RepoEntries.SINGULAR;
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

                    PriceAssert.assertThatPrice(actualPrice)
                            .isPersistedIn(priceFixtures.itemPriceRepo, SINGULAR)
                            .hasPrice(105_00)
                            .hasNoFactors();

                    EventAssert.assertThatEventV1(sharedFixtures.testEventListener.singleEvent())
                            .isPriceChange(actualPrice.getPrice().toInt(), actualPrice.getId().asString(), actualPrice.getItemId());
                }
        );
    }


}