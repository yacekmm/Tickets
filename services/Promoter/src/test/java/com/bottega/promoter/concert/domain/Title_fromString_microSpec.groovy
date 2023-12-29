package com.bottega.promoter.concert.domain


import com.bottega.promoter.fixtures.SpecificationBase

import static com.bottega.promoter.concert.domain.Title.from
import static com.bottega.promoter.concert.fixtures.TitleAssertSpock.assertThatTitle

class Title_fromString_microSpec extends SpecificationBase {

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

    //TODO tests
}