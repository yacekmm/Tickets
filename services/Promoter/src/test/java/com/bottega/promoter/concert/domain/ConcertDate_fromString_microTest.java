package com.bottega.promoter.concert.domain;

import java.util.stream.Stream;

import com.bottega.promoter.concert.fixtures.ConcertLogicTestBase;
import com.bottega.sharedlib.vo.error.ErrorResult;
import io.vavr.control.Validation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import static com.bottega.promoter.concert.api.app.ConcertErrorCode.invalid_date;
import static com.bottega.sharedlib.config.TestClockConfig.TEST_TIME;
import static com.bottega.sharedlib.vo.error.ErrorType.BAD_REQUEST;
import static java.time.LocalDate.of;
import static java.time.temporal.ChronoUnit.DAYS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ConcertDate_fromString_microTest extends ConcertLogicTestBase {

    @Test
    public void fromString_OK_onDateString(){
        //expect
        assertThat(ConcertDate.from("2022-02-22", sharedFixtures.clock).get().getUtcDate()).isEqualTo(of(2022, 2, 22));
        assertThat(ConcertDate.from("2025-01-01", sharedFixtures.clock).get().getUtcDate()).isEqualTo(of(2025, 1, 1));
        assertThat(ConcertDate.from("2025-12-31", sharedFixtures.clock).get().getUtcDate()).isEqualTo(of(2025, 12, 31));
    }

    @Test
    public void fromString_OK_onDateTimeString(){
        //expect
        assertThat(ConcertDate.from("2022-02-27T07:20:00Z", sharedFixtures.clock).get().getUtcDate()).isEqualTo(of(2022, 2, 27));
    }

    @Test
    public void fromString_badRequest_onInvalidString(){
        //when
        Validation<ErrorResult, ConcertDate> result = ConcertDate.from("invalid", sharedFixtures.clock);

        //then
        assertThat(result.getError().getType()).isEqualTo(BAD_REQUEST);
        assertThat(result.getError().getCode()).isEqualTo(invalid_date);
        assertThat(result.getError().getDescription()).startsWith("Unsupported date format: Text 'invalid' could not be parsed at index 0");
    }

    @Test
    public void fromString_badRequest_onPastDate(){
        //when
        Validation<ErrorResult, ConcertDate> result = ConcertDate.from(TEST_TIME.minus(1, DAYS).toString(), sharedFixtures.clock);

        //then
        assertThat(result.getError().getType()).isEqualTo(BAD_REQUEST);
        assertThat(result.getError().getCode()).isEqualTo(invalid_date);
        assertThat(result.getError().getDescription()).startsWith("Too early");
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
        //when
        Validation<ErrorResult, ConcertDate> result = ConcertDate.from(invalidDate, sharedFixtures.clock);

        //then
        assertThat(result.getError().getType()).isEqualTo(BAD_REQUEST);
        assertThat(result.getError().getCode()).isEqualTo(invalid_date);
        assertThat(result.getError().getDescription()).startsWith("Too early");
    }

    @Test
    public void fromString_OK_onDateRightOverThreshold(){
        //expect
        assertThat(ConcertDate.from(TEST_TIME.plus(8, DAYS).toString(), sharedFixtures.clock).get().getUtcDate()).isEqualTo(of(2022, 2, 13));
    }
}