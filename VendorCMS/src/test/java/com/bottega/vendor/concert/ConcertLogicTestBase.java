package com.bottega.vendor.concert;

import com.bottega.vendor.concert.domain.ManualConcertFixtures;
import com.bottega.vendor.tests.LogicTestBase;
import org.junit.jupiter.api.BeforeEach;

public class ConcertLogicTestBase extends LogicTestBase {

    public ManualConcertFixtures concertFixtures;

    @BeforeEach
    void setUp() {

        this.concertFixtures = ManualConcertFixtures.init();
    }
}
