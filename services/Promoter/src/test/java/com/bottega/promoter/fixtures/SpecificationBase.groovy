package com.bottega.promoter.fixtures


import com.bottega.promoter.concert.domain.ConcertFixtures
import spock.lang.Specification

class SpecificationBase extends Specification {

    SharedFixtures sharedFixtures
    ConcertFixtures concertFixtures
    ConcertReadFixtures concertReadFixtures
    PromoterFixtures promoterFixtures
    TestBuilders builders

    void setup() {
        sharedFixtures = SharedFixtures.init()
        concertFixtures = ConcertFixtures.init(sharedFixtures)
        concertReadFixtures = ConcertReadFixtures.init()
        promoterFixtures = PromoterFixtures.init()
        builders = new TestBuilders(concertFixtures.concertRepo, concertReadFixtures.concertFinderRepo, sharedFixtures.clock)
    }
}
