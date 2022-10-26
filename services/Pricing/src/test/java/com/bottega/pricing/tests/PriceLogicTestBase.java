package com.bottega.pricing.tests;

import org.junit.jupiter.api.BeforeEach;

public class PriceLogicTestBase extends LogicTestBase {


    public ManualPriceFixtures priceFixtures;

    @BeforeEach
    void setUp() {

        this.priceFixtures = ManualPriceFixtures.init();
    }
}
