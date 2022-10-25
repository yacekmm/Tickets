package com.bottega.vendor.tests.fixtures;

import com.bottega.vendor.tests.config.TestClockConfig;
import lombok.AllArgsConstructor;

import java.time.Clock;

@AllArgsConstructor
public class SharedFixtures {

    public Clock clock;

    public static SharedFixtures init() {
        return new SharedFixtures(
                new TestClockConfig().testClock());
    }
}
