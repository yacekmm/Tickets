package com.bottega.vendor.concert;

import com.bottega.vendor.concert.domain.ManualConcertFixtures;
import com.bottega.vendor.tests.UnitTestBase;
import org.junit.jupiter.api.BeforeEach;

public class ConcertTestBase extends UnitTestBase {

    public ManualConcertFixtures concertFixtures;

    @BeforeEach
    void setUp() {
        this.concertFixtures = ManualConcertFixtures.init();
    }
}
