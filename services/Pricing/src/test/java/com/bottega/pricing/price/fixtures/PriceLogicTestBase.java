package com.bottega.pricing.price.fixtures;

import com.bottega.pricing.fixtures.LogicTestBase;
import org.junit.jupiter.api.BeforeEach;

public class PriceLogicTestBase extends LogicTestBase {


    public ManualPriceFixtures priceFixtures;

    @BeforeEach
    void setUp() {

        this.priceFixtures = ManualPriceFixtures.init();
    }
}
