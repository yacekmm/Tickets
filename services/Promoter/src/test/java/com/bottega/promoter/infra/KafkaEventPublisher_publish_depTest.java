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
        //TODO prepare event with PromoterEventFactory

        //when
        //TODO publish event with sharedFixtures.eventPublisher

        //then
        //TODO assert event was received sharedFixtures.testKafkaListener and ConcertCreatedEventAssert
    }
}