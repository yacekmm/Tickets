package com.bottega.vendor.tests;

import com.bottega.vendor.tests.fixtures.SharedFixtures;
import org.junit.jupiter.api.BeforeEach;

public class UnitTestBase {

    public SharedFixtures sharedFixtures;

    @BeforeEach
    void setUp() {
        sharedFixtures = SharedFixtures.init();
    }
}
