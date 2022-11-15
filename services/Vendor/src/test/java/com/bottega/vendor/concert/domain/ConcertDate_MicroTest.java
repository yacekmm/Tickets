package com.bottega.vendor.concert.domain;

import com.bottega.vendor.concert.fixtures.ConcertLogicTestBase;
import org.junit.jupiter.api.Test;

import static java.time.LocalDate.of;
import static org.assertj.core.api.Assertions.assertThat;

class ConcertDate_MicroTest extends ConcertLogicTestBase {

    @Test
    public void fromString_OK_onDateString(){
        //expect
        assertThat(ConcertDate.from("2022-02-22", sharedFixtures.clock).get().getUtcDate()).isEqualTo(of(2022, 2, 22));
    }

}