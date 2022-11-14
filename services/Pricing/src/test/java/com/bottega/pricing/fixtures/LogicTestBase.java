package com.bottega.pricing.fixtures;

import org.junit.jupiter.api.BeforeEach;

public class LogicTestBase {

    public SharedFixtures sharedFixtures;
    public PriceFixtures priceFixtures;
    public InitPriceFixtures initPriceFixtures;
    public TestBuilders builders;

    @BeforeEach
    void setUp() {

        this.sharedFixtures = SharedFixtures.init();
        this.priceFixtures = PriceFixtures.init(sharedFixtures);
        this.initPriceFixtures = InitPriceFixtures.init();
        this.builders = new TestBuilders(priceFixtures.itemPriceRepo);
    }
}
