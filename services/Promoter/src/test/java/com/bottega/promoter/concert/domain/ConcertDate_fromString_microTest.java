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

    @Test
    public void fromString_OK_onDateString(){
        //expect
        assertThatConcertDate(from("2022-02-22", sharedFixtures.clock)).isEqualTo(2022, 2, 22);
        assertThatConcertDate(from("2025-01-01", sharedFixtures.clock)).isEqualTo(2025, 1, 1);
        assertThatConcertDate(from("2025-12-31", sharedFixtures.clock)).isEqualTo(2025, 12, 31);
    }

    @Test
    public void fromString_OK_onDateTimeString(){
        //expect
        assertThatConcertDate(from("2022-02-27T07:20:00Z", sharedFixtures.clock)).isEqualTo(2022, 2, 27);
    }

    @Test
    public void fromString_badRequest_onInvalidString(){
        //expect
        assertThatConcertDate(from("invalid", sharedFixtures.clock)).hasInvalidDateError("Unsupported date format: Text 'invalid' could not be parsed at index 0");
    }

    @Test
    public void fromString_returnsError_onPastDate(){
        //expect
        assertThatConcertDate(from(TEST_TIME.minus(1, DAYS).toString(), sharedFixtures.clock)).hasInvalidDateError("Too early");
    }

    public static Stream<Arguments> tooEarlyDatesSource() {
        return Stream.of(
                arguments(TEST_TIME.minus(1, DAYS).toString()),
                arguments(TEST_TIME.toString()),
                arguments(TEST_TIME.plus(6, DAYS).toString()),
                arguments(TEST_TIME.plus(7, DAYS).toString())
        );
    }

    @ParameterizedTest
    @MethodSource("tooEarlyDatesSource")
    public void fromString_returnsError_onDateUnderMinimumThreshold(String invalidDate){
        //expect
        assertThatConcertDate(from(invalidDate, sharedFixtures.clock)).hasInvalidDateError("Too early");
    }

    @Test
    public void fromString_OK_onDateRightOverThreshold(){
        //expect
        assertThatConcertDate(from(TEST_TIME.plus(8, DAYS).toString(), sharedFixtures.clock)).isValid();
    }
}