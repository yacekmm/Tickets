package com.bottega.pricing.fixtures;

import com.bottega.sharedlib.event.EventPublisher;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SharedFixtures {

    //infra
    public EventPublisher eventPublisher;

    private final EmbeddedKafkaBroker kafkaBroker;

    public static SharedFixtures init() {
        SharedFixtures fixtures = new SharedFixtures(null);

        initInfra(fixtures);

        return fixtures;
    }

    private static void initInfra(SharedFixtures fixtures) {
        fixtures.eventPublisher = new FakeEventPublisher();
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
