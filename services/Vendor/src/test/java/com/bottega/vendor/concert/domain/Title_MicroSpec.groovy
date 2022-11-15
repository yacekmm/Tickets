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
}