package com.bottega.pricing.fixtures;

import com.bottega.pricing.infra.TestKafkaEventListener;
import com.bottega.sharedlib.event.EventPublisher;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

@Component
public class SharedFixtures {

    //infra
    public EventPublisher eventPublisher;

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

    public void inTransaction(Runnable runnable) {
        transactionTemplate.execute((status) -> {
            runnable.run();
            return null;
        });
    }

    @SneakyThrows
    public void tearDown() {
        testEventListener.tearDown();
    }
}
