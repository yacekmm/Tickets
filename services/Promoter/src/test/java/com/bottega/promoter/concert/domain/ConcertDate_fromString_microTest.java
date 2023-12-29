package com.bottega.promoter.concert.domain;

import java.util.stream.Stream;

import com.bottega.promoter.concert.fixtures.ConcertLogicTestBase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import static com.bottega.promoter.concert.domain.ConcertDate.from;
import static com.bottega.promoter.concert.fixtures.ConcertDateAssert.assertThatConcertDate;
import static com.bottega.sharedlib.config.TestClockConfig.TEST_TIME;
import static java.time.LocalDate.of;
import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ConcertDate_fromString_microTest extends ConcertLogicTestBase {

    //TODO tests

    @Test
    public void fromString_OK_onDateString(){
        //expect
        Assertions.assertThat(ConcertDate.from("2022-02-22", sharedFixtures.clock).get().getUtcDate()).isEqualTo(of(2022, 2, 22));
    }

}