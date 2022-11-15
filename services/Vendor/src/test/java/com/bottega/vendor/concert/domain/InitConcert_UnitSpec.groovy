package com.bottega.vendor.concert.domain

import com.bottega.sharedlib.config.TestClockConfig
import com.bottega.vendor.fixtures.SpecificationBase

class InitConcert_UnitSpec extends SpecificationBase {

    def "initConcert - adds tags"() {
        given:


        when:


        then:

    }

    def "initConcert - assigns category"() {
        given:
        def newConcert = new Concert(new ConcertId(), Title.from(title).get(), ConcertDate.from(TestClockConfig.TEST_TIME_PLUS_30_DAYS.toString(), sharedFixtures.clock).get(), "vendor-id", new HashSet<>(), null)

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