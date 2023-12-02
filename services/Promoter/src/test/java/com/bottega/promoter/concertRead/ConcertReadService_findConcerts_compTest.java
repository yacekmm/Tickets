package com.bottega.promoter.concertRead;

import java.util.List;

import com.bottega.promoter.concert.domain.Concert;
import com.bottega.promoter.concert.fixtures.ConcertLogicTestBase;
import org.junit.jupiter.api.Test;
import static com.bottega.sharedlib.config.TestClockConfig.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ConcertReadService_findConcerts_compTest extends ConcertLogicTestBase {


    @Test
    public void findConcerts_returnsSortedConcertsForPromoter_onValidRequest() {
        //given
        Concert concert_1 = builders.aConcert().withDate(TEST_TIME_PLUS_30_DAYS).withPromoterId("promoter").inFinderDb();
        Concert concert_2 = builders.aConcert().withDate(TEST_TIME_PLUS_60_DAYS).withPromoterId("promoter").inFinderDb();
        builders.aConcert().withPromoterId("other").inDb();

        //when
        List<Concert> result = concertReadFixtures.concertReadService.findConcertsForPromoter("promoter");

        //then
        assertThat(result).containsExactly(concert_1, concert_2);
    }

}
