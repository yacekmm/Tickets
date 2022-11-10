package com.bottega.vendor.concert.domain


import com.bottega.vendor.fixtures.SpecificationBase

import static com.bottega.sharedlib.vo.error.ErrorType.BAD_REQUEST
import static com.bottega.vendor.concert.api.app.ConcertErrorCode.invalid_title
import static com.bottega.vendor.concert.domain.Title.from

class Title_SpockMicroTest extends SpecificationBase {

    def "fromString - OK - on valid input"() {

        expect:
        from(titleString).get().value == expectedTitle

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
        with(from(titleString).getError()) {
            type == BAD_REQUEST
            code == invalid_title
            description == "Title length must be between 10 and 160 chars"
        }

        where:
        titleString << [
                null,
                "a" * 9,
                "a" * 161,
                "a" * 9 + " "]
    }

    def "fromString - returns error - on banned words in title"() {

        expect:
        with(from(titleString).getError()) {
            type == BAD_REQUEST
            code == invalid_title
            description == "Title must not contain banned word: " + expectedBannedWord
        }

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