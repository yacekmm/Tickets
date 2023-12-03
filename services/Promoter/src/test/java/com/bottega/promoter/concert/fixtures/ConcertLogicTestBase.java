package com.bottega.promoter.concert.fixtures;

import com.bottega.promoter.concert.domain.ConcertFixtures;
import com.bottega.promoter.fixtures.*;
import org.junit.jupiter.api.BeforeEach;

public class ConcertLogicTestBase extends LogicTestBase {

    public ConcertFixtures concertFixtures;
    public ConcertReadFixtures concertReadFixtures;
    public PromoterFixtures promoterFixtures;
    public TestBuilders builders;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        this.concertFixtures = ConcertFixtures.init(sharedFixtures);
        this.concertReadFixtures = ConcertReadFixtures.init();
        this.promoterFixtures = PromoterFixtures.init();
        this.builders = new TestBuilders(concertFixtures.concertRepo, concertReadFixtures.concertFinderRepo, sharedFixtures.clock);
    }
}
