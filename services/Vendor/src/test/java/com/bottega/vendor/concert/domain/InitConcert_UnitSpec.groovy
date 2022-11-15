package com.bottega.vendor.concert.domain


import com.bottega.vendor.fixtures.SpecificationBase

class InitConcert_UnitSpec extends SpecificationBase {

    def "initConcert - assigns category"() {
        given:
        Concert newConcert = builders.aConcert().withTitle(title).build()

        when:
        newConcert.initNewConcert(concertFixtures.categoryService)

        then:
        newConcert.category.value == expectedCategory

        where:
        title                          | expectedCategory
        "no category"                  | "other"
        "Rock concert"                 | "rock"
        "Scorpions in Warsaw!"         | "rock"
        "Scorpions on Mystic Festival" | "rock"
        "Rihanna the best of"          | "superstar"
    }
}