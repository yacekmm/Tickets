package com.bottega.promoter.concert.domain;

import java.util.stream.Stream;

import com.bottega.promoter.concert.fixtures.ConcertLogicTestBase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import static com.bottega.promoter.concert.domain.ConcertDate.from;
import static com.bottega.promoter.concert.fixtures.ConcertDateAssert.assertThatConcertDate;
import static com.bottega.sharedlib.config.TestClockConfig.TEST_TIME;
import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ConcertDate_fromString_microTest extends ConcertLogicTestBase {

    //TODO tests

    @Test
    public void fromString_OK_onDateString(){
        //given

        //when

        //then

    }

}