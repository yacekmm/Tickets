package com.bottega.vendor.fixtures;

import com.bottega.sharedlib.config.TestClockConfig;
import com.bottega.sharedlib.event.EventPublisher;
import com.bottega.sharedlib.infra.repo.FakeEventPublisher;
import lombok.AllArgsConstructor;

import java.time.Clock;

@AllArgsConstructor
public class SharedFixtures {

    public Clock clock;
    private EventPublisher eventPublisher;

    public static SharedFixtures init() {
        return new SharedFixtures(
                new TestClockConfig().testClock(),
                new FakeEventPublisher());
    }

    public FakeEventPublisher fakeEventPublisher() {
        return (FakeEventPublisher) eventPublisher;
    }
}
