package com.bottega.vendor.concert.fixtures;

import com.bottega.vendor.concert.domain.ConcertFixtures;
import com.bottega.vendor.fixtures.LogicTestBase;
import org.junit.jupiter.api.BeforeEach;

public class ConcertLogicTestBase extends LogicTestBase {

    public ConcertFixtures concertFixtures;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        this.concertFixtures = ConcertFixtures.init(sharedFixtures);
    }
}
