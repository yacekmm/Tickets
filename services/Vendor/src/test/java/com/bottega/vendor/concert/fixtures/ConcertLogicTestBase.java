package com.bottega.vendor.concert.fixtures;

import com.bottega.vendor.agreements.fixtures.VendorFixtures;
import com.bottega.vendor.concert.domain.ConcertFixtures;
import com.bottega.vendor.fixtures.*;
import org.junit.jupiter.api.BeforeEach;

public class ConcertLogicTestBase extends LogicTestBase {

    public ConcertFixtures concertFixtures;
    public ConcertReadFixtures concertReadFixtures;
    public VendorFixtures vendorFixtures;
    public TestBuilders builders;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        this.concertFixtures = ConcertFixtures.init(sharedFixtures);
        this.concertReadFixtures = ConcertReadFixtures.init();
        this.vendorFixtures = VendorFixtures.init();
        this.builders = new TestBuilders(concertFixtures.concertRepo, concertReadFixtures.concertFinderRepo, sharedFixtures.clock);
    }
}
