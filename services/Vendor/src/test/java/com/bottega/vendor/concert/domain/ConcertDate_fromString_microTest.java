package com.bottega.vendor.concert.domain;

import com.bottega.vendor.concert.fixtures.*;
import org.junit.jupiter.api.Test;
import static com.bottega.sharedlib.config.TestClockConfig.TEST_TIME;
import static com.bottega.vendor.concert.domain.ConcertDate.from;
import static java.time.temporal.ChronoUnit.DAYS;

class ConcertDate_fromString_microTest extends ConcertLogicTestBase {

    @Test
    public void fromString_OK_onDateString(){
        //expect
        ConcertDateAssert.assertThatConcertDate(from("2022-02-22", sharedFixtures.clock)).isEqualTo(2022, 2, 22);
        ConcertDateAssert.assertThatConcertDate(from("2025-01-01", sharedFixtures.clock)).isEqualTo(2025, 1, 1);
        ConcertDateAssert.assertThatConcertDate(from("2025-12-31", sharedFixtures.clock)).isEqualTo(2025, 12, 31);
    }

    @Test
    public void fromString_OK_onDateTimeString(){
        //expect
        ConcertDateAssert.assertThatConcertDate(from("2022-02-27T07:20:00Z", sharedFixtures.clock)).isEqualTo(2022, 2, 27);
    }

    @Test
    public void fromString_badRequest_onInvalidString(){
        //expect
        ConcertDateAssert.assertThatConcertDate(from("invalid", sharedFixtures.clock)).hasInvalidDateError("Unsupported date format: Text 'invalid' could not be parsed at index 0");
    }

    @Test
    public void fromString_error_onPastDate(){
        //expect
        ConcertDateAssert.assertThatConcertDate(from(TEST_TIME.minus(1, DAYS).toString(), sharedFixtures.clock)).hasInvalidDateError("Too early");
        ConcertDateAssert.assertThatConcertDate(from(TEST_TIME.minus(30, DAYS).toString(), sharedFixtures.clock)).hasInvalidDateError("Too early");
    }

    @Test
    public void fromString_error_onDateUnderMinimumThreshold(){
        //expect
        ConcertDateAssert.assertThatConcertDate(from(TEST_TIME.plus(6, DAYS).toString(), sharedFixtures.clock)).hasInvalidDateError("Too early");
        ConcertDateAssert.assertThatConcertDate(from(TEST_TIME.plus(7, DAYS).toString(), sharedFixtures.clock)).hasInvalidDateError("Too early");
        ConcertDateAssert.assertThatConcertDate(from(TEST_TIME.plus(8, DAYS).toString(), sharedFixtures.clock)).isValid();
        ConcertDateAssert.assertThatConcertDate(from(TEST_TIME.plus(9, DAYS).toString(), sharedFixtures.clock)).isValid();
    }
}