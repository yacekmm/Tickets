package com.bottega.vendor.concert.domain;

import com.bottega.vendor.concert.api.app.ConcertService;
import com.bottega.vendor.concert.fixtures.InMemoryConcertRepo;
import com.bottega.vendor.concert.fixtures.fixtures.ConcertBuilder;
import com.bottega.vendor.concert.infra.repo.ConcertRepo;
import com.bottega.vendor.fixtures.FakeConcertClient;
import com.bottega.vendor.fixtures.SharedFixtures;

public class ManualConcertFixtures {

    //SUTs
    public ConcertService concertService;

    //infrastructure
    public ConcertRepo concertRepo;

    //builders
    public ConcertBuilder concertBuilder;

    public static ManualConcertFixtures init(SharedFixtures sharedFixtures) {
        ManualConcertFixtures concertFixtures = new ManualConcertFixtures();

        initInfrastructure(concertFixtures);
        initSut(concertFixtures, sharedFixtures);
        initBuilders(concertFixtures);

        return concertFixtures;
    }

    private static void initInfrastructure(ManualConcertFixtures concertFixtures) {
        concertFixtures.concertRepo = new InMemoryConcertRepo();
    }

    private static void initSut(ManualConcertFixtures concertFixtures, SharedFixtures sharedFixtures) {
        concertFixtures.concertService = new ConcertService(
                new ConcertFactory(),
                concertFixtures.concertRepo,
                new FakeConcertClient(),
                sharedFixtures.fakeEventPublisher()
        );
    }

    private static void initBuilders(ManualConcertFixtures concertFixtures) {
        concertFixtures.concertBuilder = new ConcertBuilder(concertFixtures.concertRepo);
    }


}
