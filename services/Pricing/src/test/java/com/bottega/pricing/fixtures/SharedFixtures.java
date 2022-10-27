package com.bottega.pricing.fixtures;

public class SharedFixtures {

    //infra
    public FakeEventPublisher eventPublisher;

    public static SharedFixtures init() {
        SharedFixtures fixtures = new SharedFixtures();

        initInfra(fixtures);

        return fixtures;
    }

    private static void initInfra(SharedFixtures fixtures) {
        fixtures.eventPublisher = new FakeEventPublisher();
    }
}
