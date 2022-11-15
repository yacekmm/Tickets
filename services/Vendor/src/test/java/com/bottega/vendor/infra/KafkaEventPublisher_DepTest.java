package com.bottega.vendor.infra;

import com.bottega.sharedlib.event.Event;
import com.bottega.sharedlib.fixtures.EventAssert;
import com.bottega.vendor.concert.domain.Concert;
import com.bottega.vendor.fixtures.FrameworkTestBase;
import org.junit.jupiter.api.Test;

import static com.bottega.vendor.concert.domain.VendorEventFactory.concertCreated;

class KafkaEventPublisher_DepTest extends FrameworkTestBase {

    @Test
    public void publishEvent_OK(){
        //given
        Concert concert = builders.dontLook().build();
        Event concertCreated = concertCreated(concert, 2);

        //when
        sharedFixtures.eventPublisher.publish(concertCreated);

        //then
        EventAssert.assertThatEventV1(sharedFixtures.testKafkaListener.singleEvent())
                .isConcertCreated(concert.getId().asString(), concert.getTitle().getValue(), concert.getDate().getUtcDate().toString(), new String[]{}, 2);
    }
}