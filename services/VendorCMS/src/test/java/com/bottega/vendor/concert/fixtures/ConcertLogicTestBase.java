package com.bottega.vendor.concert.fixtures;

import com.bottega.vendor.concert.domain.ManualConcertFixtures;
import com.bottega.vendor.fixtures.LogicTestBase;
import org.junit.jupiter.api.BeforeEach;

public class ConcertLogicTestBase extends LogicTestBase {

    public ManualConcertFixtures concertFixtures;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        this.concertFixtures = ManualConcertFixtures.init(sharedFixtures);
    }
}
