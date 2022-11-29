package com.bottega.vendor.fixtures

import com.bottega.vendor.agreements.fixtures.VendorFixtures
import com.bottega.vendor.concert.domain.ConcertFixtures
import spock.lang.Specification

class SpecificationBase extends Specification {

    SharedFixtures sharedFixtures
    ConcertFixtures concertFixtures
    ConcertReadFixtures concertReadFixtures
    VendorFixtures vendorFixtures
    TestBuilders builders

    void setup() {
        sharedFixtures = SharedFixtures.init()
        concertFixtures = ConcertFixtures.init(sharedFixtures)
        concertReadFixtures = ConcertReadFixtures.init()
        vendorFixtures = VendorFixtures.init()
        builders = new TestBuilders(concertFixtures.concertRepo, concertReadFixtures.concertFinderRepo, sharedFixtures.clock)
    }
}
