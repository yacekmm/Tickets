package com.bottega.vendor.concert.domain

import com.bottega.vendor.fixtures.SpecificationBase

import static java.util.stream.Collectors.toSet

class InitConcert_UnitSpec extends SpecificationBase {

    def "initConcert - adds tags"() {
        given:
        Concert newConcert = builders.aConcert().withTitle(title).build()

        when:
        newConcert.initNewConcert(concertFixtures.tagService, concertFixtures.categoryService)

        then:
        with(newConcert.getTags().stream().map(Tag::getValue).collect(toSet())) {
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
        Concert newConcert = builders.aConcert().withTitle(title).build()

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