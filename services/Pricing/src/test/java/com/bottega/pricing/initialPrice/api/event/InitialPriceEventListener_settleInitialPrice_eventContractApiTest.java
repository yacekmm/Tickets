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
        //TODO trigger stub named "triggerConcertCreatedEvent" with StubTrigger

        //then
        //TODO wait with Awaitility until price is persisted in priceFixtures.itemPriceRepo

        ItemPrice actualPrice = priceFixtures.itemPriceRepo.findAll().iterator().next();

        //TODO assert that actualPrice is equal to 105_00 with no factor, using PriceAssert

        //TODO assert that PriceChange event is published with PriceChangeEventAssert
    }


}