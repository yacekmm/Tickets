package com.bottega.promoter.infra;

import com.bottega.promoter.concert.domain.Concert;
import com.bottega.promoter.fixtures.FrameworkTestBase;
import com.bottega.sharedlib.event.Event;
import com.bottega.sharedlib.fixtures.EventAssert;
import org.junit.jupiter.api.Test;
import static com.bottega.promoter.concert.domain.PromoterEventFactory.concertCreated;
import static com.bottega.sharedlib.fixtures.EventAssert.assertThatEventV1;

class KafkaEventPublisher_publish_depTest extends FrameworkTestBase {

    @Test
    public void publishEvent_OK(){
        //given
        Concert concert = builders.aConcert().build();
        Event concertCreated = concertCreated(concert, 2);

        //when
        sharedFixtures.eventPublisher.publish(concertCreated);

        //then
        assertThatEventV1(sharedFixtures.testKafkaListener.singleEvent())
                .isConcertCreated(concert.getId().asString(), concert.getTitle().getValue(), concert.getDate().getUtcDate().toString(), new String[]{}, 2);
    }
}