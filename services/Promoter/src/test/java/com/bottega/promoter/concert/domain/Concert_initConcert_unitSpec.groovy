package com.bottega.promoter.concert.domain

import com.bottega.promoter.fixtures.SpecificationBase

class Concert_initConcert_unitSpec extends SpecificationBase {

    def "initConcert - adds tags"() {
        given:
        Concert newConcert = builders.aConcert().withTitle(title).build()

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
        //TODO more cases
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