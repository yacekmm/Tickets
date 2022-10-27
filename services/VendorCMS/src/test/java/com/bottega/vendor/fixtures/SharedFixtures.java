package com.bottega.vendor.fixtures;

import com.bottega.sharedlib.config.TestClockConfig;
import com.bottega.sharedlib.event.EventPublisher;
import com.bottega.sharedlib.infra.repo.FakeEventPublisher;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.stereotype.Component;

import java.time.Clock;

@Component
@RequiredArgsConstructor
public class SharedFixtures {

    public final Clock clock;
    private final EventPublisher eventPublisher;
    private final EmbeddedKafkaBroker kafkaBroker;

    public static SharedFixtures init() {
        return new SharedFixtures(
                new TestClockConfig().testClock(),
                new FakeEventPublisher(),
                null);
    }

    public FakeEventPublisher fakeEventPublisher() {
        return (FakeEventPublisher) eventPublisher;
    }

    @SneakyThrows
    public void tearDown() {
        kafkaBroker.doWithAdmin(adminClient -> Try.of(() ->
                adminClient.deleteTopics(kafkaBroker.getTopics()).all().get()));
    }
}
