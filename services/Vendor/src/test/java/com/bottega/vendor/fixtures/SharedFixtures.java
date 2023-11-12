package com.bottega.vendor.fixtures;

import java.time.Clock;

import com.bottega.sharedlib.config.TestClockConfig;
import com.bottega.sharedlib.event.EventPublisher;
import com.bottega.vendor.infra.TestKafkaEventListener;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SharedFixtures {

    @Autowired
    public Clock clock;

    @Autowired
    public EventPublisher eventPublisher;

    @Autowired
    public TestKafkaEventListener testKafkaListener;

    public static SharedFixtures init() {
        SharedFixtures fixtures = new SharedFixtures();

        fixtures.clock = new TestClockConfig().testClock();
        fixtures.eventPublisher = new FakeEventPublisher();

        return fixtures;
    }

    public FakeEventPublisher fakeEventPublisher() {
        return (FakeEventPublisher) eventPublisher;
    }

    @SneakyThrows
    public void tearDown() {
        testKafkaListener.tearDown();
    }
}
