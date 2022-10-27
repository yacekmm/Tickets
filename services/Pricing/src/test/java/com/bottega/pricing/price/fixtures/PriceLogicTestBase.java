package com.bottega.pricing.price.fixtures;

import com.bottega.pricing.fixtures.LogicTestBase;
import com.bottega.pricing.fixtures.SharedFixtures;
import org.junit.jupiter.api.BeforeEach;

public class PriceLogicTestBase extends LogicTestBase {


    public SharedFixtures sharedFixtures;
    public ManualPriceFixtures priceFixtures;

    @BeforeEach
    void setUp() {

        this.sharedFixtures = SharedFixtures.init();
        this.priceFixtures = ManualPriceFixtures.init(sharedFixtures);
    }
}
