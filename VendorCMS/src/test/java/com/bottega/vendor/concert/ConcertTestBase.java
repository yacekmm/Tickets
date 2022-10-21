package com.bottega.vendor.concert;

import com.bottega.vendor.concert.domain.ConcertFixtures;
import com.bottega.vendor.tests.UnitTestBase;
import org.junit.jupiter.api.BeforeEach;

public class ConcertTestBase extends UnitTestBase {

    public ConcertFixtures concertFixtures;

    @BeforeEach
    void setUp() {
        this.concertFixtures = ConcertFixtures.init();
    }
}
