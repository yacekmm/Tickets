package com.bottega.promoter.concertRead;

import com.bottega.promoter.fixtures.FrameworkTestBase;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

class PriceChangeEventListener_updatesReadModel_pactMessagingConsumerApiTest extends FrameworkTestBase {

    //TODO: append suffix `.Messaging`  Provider and Consumer names to distinguish it from previous assignments

    @BeforeEach
    void setUp() {
        super.beforeEach();
        System.setProperty("pact.rootDir", "build/pacts");
    }

    @Test
    @SneakyThrows
    public void settleInitialPrice_createsPrice_onConcertCreatedEvent() {
        //when
        //TODO: trigger events to consume

        //then
        await().until(() -> concertReadFixtures.concertPriceRepo.findAll().iterator().hasNext());
        ConcertPrice concertPrice = concertReadFixtures.concertPriceRepo.findAll().iterator().next();
        Assertions.assertThat(concertPrice.getPrice()).isEqualTo(100_00);
    }


}