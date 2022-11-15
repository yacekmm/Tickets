package com.bottega.vendor.concert.domain

import com.bottega.vendor.fixtures.SpecificationBase

import static com.bottega.sharedlib.config.TestClockConfig.TEST_TIME
import static com.bottega.vendor.concert.domain.ConcertDate.from
import static com.bottega.vendor.concert.fixtures.ConcertDateAssertSpock.assertThatConcertDate
import static java.time.LocalDate.of
import static java.time.temporal.ChronoUnit.DAYS

class ConcertDate_MicroSpec extends SpecificationBase {

    def "fromString - OK - on valid input"() {

        expect:
        assertThatConcertDate(from(dateString, sharedFixtures.clock))
                .isEqualTo(expectedDate)

        where:
        dateString                         | expectedDate
        "2022-02-22"                       | of(2022, 2, 22)
        "2025-01-01"                       | of(2025, 1, 1)
        "2025-12-31"                       | of(2025, 12, 31)
        "2022-02-27T07:20:00Z"             | of(2022, 2, 27)

        //closest acceptable date after minDate threshold
        TEST_TIME.plus(8, DAYS).toString() | of(2022, 2, 13)
    }

    def "fromString - returns error - on invalid input"() {

        expect:
        assertThatConcertDate(from(dateString, sharedFixtures.clock))
                .hasInvalidDateError(expectedDesc)


        where:
        dateString                           | expectedDesc
        "invalid"                            | "Unsupported date format: Text 'invalid' could not be parsed at index 0"

        //date has to be in future + threshold in days
        TEST_TIME.minus(1, DAYS).toString()  | "Too early"
        TEST_TIME.minus(30, DAYS).toString() | "Too early"
        TEST_TIME.plus(7, DAYS).toString()   | "Too early"    // latest `too early` date
    }
}
