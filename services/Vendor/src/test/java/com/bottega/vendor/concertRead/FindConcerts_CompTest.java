package com.bottega.vendor.concertRead;

import com.bottega.vendor.concert.domain.Concert;
import com.bottega.vendor.concert.fixtures.ConcertLogicTestBase;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.bottega.sharedlib.config.TestClockConfig.*;
import static org.assertj.core.api.Assertions.assertThat;

public class FindConcerts_CompTest extends ConcertLogicTestBase {


    @Test
    public void findConcerts_returnsSortedConcertsForVendor_onValidRequest() {
        Concert concert_1 = builders.aConcert().withDate(TEST_TIME_PLUS_30_DAYS).withVendorId("vendor").inFinderDb();
        Concert concert_2 = builders.aConcert().withDate(TEST_TIME_PLUS_60_DAYS).withVendorId("vendor").inFinderDb();
        builders.aConcert().withVendorId("other").inDb();

        //when
        List<Concert> result = concertReadFixtures.concertReadService.findConcertsForVendor("vendor");

        //then
        assertThat(result).containsExactly(concert_1, concert_2);
    }

}
