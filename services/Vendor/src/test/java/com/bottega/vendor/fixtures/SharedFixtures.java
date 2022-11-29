package com.bottega.vendor.fixtures;

import com.bottega.sharedlib.config.TestClockConfig;
import com.bottega.sharedlib.event.EventPublisher;
import com.bottega.vendor.infra.TestKafkaEventListener;
import io.vavr.control.Try;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.stereotype.Component;

import java.time.Clock;

@Component
public class SharedFixtures {

    @Autowired
    public Clock clock;

    @Autowired
    public EventPublisher eventPublisher;

    @Autowired
    public TestKafkaEventListener testKafkaListener;

    @Autowired
    private EmbeddedKafkaBroker kafkaBroker;

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
        kafkaBroker.doWithAdmin(adminClient -> Try.of(() ->
                adminClient.deleteTopics(kafkaBroker.getTopics()).all().get()));
        testKafkaListener.tearDown();
    }
}
