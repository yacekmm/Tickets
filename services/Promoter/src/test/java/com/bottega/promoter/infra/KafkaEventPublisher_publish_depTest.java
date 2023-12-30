package com.bottega.promoter.infra;

import com.bottega.promoter.concert.domain.Concert;
import com.bottega.promoter.concert.fixtures.ConcertCreatedEventAssert;
import com.bottega.promoter.fixtures.FrameworkTestBase;
import com.bottega.sharedlib.event.Event;
import org.junit.jupiter.api.Test;
import static com.bottega.promoter.concert.domain.PromoterEventFactory.concertCreated;

class KafkaEventPublisher_publish_depTest extends FrameworkTestBase {

    @Test
    public void publishEvent_OK(){
        //given
        Concert concert = builders.aConcert().build();
        Event concertCreated = concertCreated(concert, 2);

        //when
        sharedFixtures.eventPublisher.publish(concertCreated);

        //then
        ConcertCreatedEventAssert.assertThatEvent(sharedFixtures.testKafkaListener.singleEvent())
                .isConcertCreatedV1(concert, 2);
    }
}