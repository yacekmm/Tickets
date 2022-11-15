package com.bottega.vendor.concert.domain

import com.bottega.vendor.fixtures.SpecificationBase

import static com.bottega.sharedlib.config.TestClockConfig.TEST_TIME_PLUS_30_DAYS

class InitConcert_UnitSpec extends SpecificationBase {

    def "initConcert - adds tags"() {
        given:
        def newConcert = new Concert(new ConcertId(), Title.from(title).get(), ConcertDate.from(TEST_TIME_PLUS_30_DAYS.toString(), sharedFixtures.clock).get(), "vendor-id", new HashSet<>(), null)

        when:
        newConcert.initNewConcert(concertFixtures.tagService, concertFixtures.categoryService)

        then:
        with(newConcert.getTags().stream().map(Tag::getValue).toList()) {
            size() == expectedTags.size()
            containsAll(expectedTags)
        }

        where:
        title                          | expectedTags
        "no tags apply"                | []
        "Rock concert"                 | ["rock"]
        "Scorpions in Warsaw!"         | ["rock"]
        "Scorpions on Mystic Festival" | ["rock", "festival"]
        "Rihanna the best of"          | ["pop"]
    }

    def "initConcert - assigns category"() {
        given:
        def newConcert = new Concert(new ConcertId(), Title.from(title).get(), ConcertDate.from(TEST_TIME_PLUS_30_DAYS.toString(), sharedFixtures.clock).get(), "vendor-id", new HashSet<>(), null)

        when:
        newConcert.initNewConcert(concertFixtures.tagService, concertFixtures.categoryService)

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