package com.bottega.pricing.fixtures;

import org.junit.jupiter.api.BeforeEach;

public class LogicTestBase {

    public SharedFixtures sharedFixtures;
    public ManualPriceFixtures priceFixtures;
    public ManualInitPriceFixtures initPriceFixtures;

    @BeforeEach
    void setUp() {

        this.sharedFixtures = SharedFixtures.init();
        this.priceFixtures = ManualPriceFixtures.init(sharedFixtures);
        this.initPriceFixtures = ManualInitPriceFixtures.init();
    }
}
