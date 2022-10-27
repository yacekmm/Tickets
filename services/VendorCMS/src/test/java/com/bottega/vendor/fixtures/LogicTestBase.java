package com.bottega.vendor.fixtures;

import org.junit.jupiter.api.BeforeEach;

public class LogicTestBase {

    public SharedFixtures sharedFixtures;

    @BeforeEach
    protected void setUp() {
        sharedFixtures = SharedFixtures.init();
    }
}
