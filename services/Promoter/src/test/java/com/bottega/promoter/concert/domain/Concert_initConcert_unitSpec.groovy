package com.bottega.promoter.concert.domain


import com.bottega.promoter.fixtures.SpecificationBase
import com.bottega.sharedlib.config.TestClockConfig

import static com.bottega.sharedlib.config.TestClockConfig.TEST_TIME_PLUS_30_DAYS

class Concert_initConcert_unitSpec extends SpecificationBase {

    def "initConcert - assigns category"() {
        given:
        def newConcert = new Concert(new ConcertId(), Title.from(title).get(), ConcertDate.from(TEST_TIME_PLUS_30_DAYS.toString(), sharedFixtures.clock).get(), "vendor-id", new HashSet<>(), null)
        def categoryService = Mock(CategoryService.class)
        categoryService.categorize(Title.from(title).get()) >> Category.from(expectedCategory)

        when:
        newConcert.initNewConcert(categoryService)

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