package com.bottega.pricing.fixtures;

import com.bottega.pricing.infra.TestKafkaEventListener;
import com.bottega.sharedlib.event.EventPublisher;
import io.vavr.control.Try;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

@Component
public class SharedFixtures {

    //infra
    public EventPublisher eventPublisher;

    @Autowired
    private EmbeddedKafkaBroker kafkaBroker;
    @Autowired
    public TestKafkaEventListener testEventListener;
    @Autowired
    private TransactionTemplate transactionTemplate;

    public static SharedFixtures init() {
        SharedFixtures fixtures = new SharedFixtures();

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
        testEventListener.tearDown();
    }

    public void inTransaction(Runnable runnable) {
        transactionTemplate.execute((status) -> {
            runnable.run();
            return null;
        });
    }
}
