package com.bottega.vendor.concert.domain

import com.bottega.vendor.fixtures.SpecificationBase

import static com.bottega.vendor.concert.domain.Title.from
import static com.bottega.vendor.concert.fixtures.TitleAssertSpock.assertThatTitle

class Title_MicroSpec extends SpecificationBase {

    def "fromString - OK - on valid input"() {

        expect:
        assertThatTitle(from(titleString))
                .isEqualTo(expectedTitle)

        where:
        titleString                  | expectedTitle
        "Valid title for a concert"  | "Valid title for a concert"
        "     trims whitespaces    " | "trims whitespaces"

        //accepts within length limits
        "a".multiply(10)             | "a".multiply(10)
        "a".multiply(160)            | "a".multiply(160)
        "a".multiply(160) + " "      | "a".multiply(160)
    }

    def "fromString - returns error - on invalid length"() {

        expect:
        assertThatTitle(from(titleString))
                .hasInvalidLengthError()

        where:
        titleString << [
                null,
                "a" * 9,
                "a" * 161,
                "a" * 9 + " "]
    }

    def "fromString - returns error - on banned words in title"() {

        expect:
        assertThatTitle(from(titleString))
                .hasBannedWordError(expectedBannedWord)

        where:
        titleString                | expectedBannedWord
        "rage against the machine" | "rage"
        "VIOLENCE"                 | "violence"
        "   Snickers   "           | "snickers"
        "rage against the machine" | "rage"
        "machine against the rage" | "rage"
        "machine rage machine"     | "rage"
    }
}