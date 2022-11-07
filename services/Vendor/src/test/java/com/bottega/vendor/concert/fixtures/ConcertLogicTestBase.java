package com.bottega.vendor.concert.fixtures;

import com.bottega.vendor.agreements.fixtures.VendorFixtures;
import com.bottega.vendor.concert.domain.ConcertFixtures;
import com.bottega.vendor.fixtures.LogicTestBase;
import org.junit.jupiter.api.BeforeEach;

public class ConcertLogicTestBase extends LogicTestBase {

    public ConcertFixtures concertFixtures;
    public VendorFixtures vendorFixtures;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        this.concertFixtures = ConcertFixtures.init(sharedFixtures);
        this.vendorFixtures = VendorFixtures.init();
    }
}
