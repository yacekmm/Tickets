package com.bottega.vendor.concert.domain;

import com.bottega.vendor.concert.application.api.ConcertService;
import com.bottega.vendor.concert.infra.repo.ConcertRepo;
import com.bottega.vendor.concert.infra.repo.InMemoryConcertRepo;
import com.bottega.vendor.concert.tests.fixtures.ConcertBuilder;
import com.bottega.vendor.infra.client.pricing.FakeConcertClient;

public class ManualConcertFixtures {

    //SUTs
    public ConcertService concertService;

    //infrastructure
    public ConcertRepo concertRepo;

    //builders
    public ConcertBuilder concertBuilder;

    public static ManualConcertFixtures init() {
        ManualConcertFixtures concertFixtures = new ManualConcertFixtures();

        initInfrastructure(concertFixtures);
        initSut(concertFixtures);
        initBuilders(concertFixtures);

        return concertFixtures;
    }

    private static void initInfrastructure(ManualConcertFixtures concertFixtures) {
        concertFixtures.concertRepo = new InMemoryConcertRepo();
    }

    private static void initSut(ManualConcertFixtures concertFixtures) {
        concertFixtures.concertService = new ConcertService(
                new ConcertFactory(),
                concertFixtures.concertRepo,
                new FakeConcertClient()
        );
    }

    private static void initBuilders(ManualConcertFixtures concertFixtures) {
        concertFixtures.concertBuilder = new ConcertBuilder(concertFixtures.concertRepo);
    }


}
