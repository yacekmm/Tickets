package com.bottega.vendor.concert.domain;

import com.bottega.sharedlib.config.TestClockConfig;
import com.bottega.vendor.concert.fixtures.ConcertLogicTestBase;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

class InitConcert_UnitTest extends ConcertLogicTestBase {

    @Test
    public void initConcert_assignsCategory() {
        //given
        Concert newConcert = new Concert(new ConcertId(), Title.from("rock concert").get(), ConcertDate.from(TestClockConfig.TEST_TIME_PLUS_30_DAYS.toString(), sharedFixtures.clock).get(), "vendor-id", new HashSet<>(), null);

        //when
        newConcert.initNewConcert();

        //then
        assertThat(newConcert.getCategory().getValue()).isEqualTo("rock");
    }
}