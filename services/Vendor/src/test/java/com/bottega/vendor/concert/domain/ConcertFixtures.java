package com.bottega.vendor.concert.domain;

import com.bottega.vendor.concert.api.app.ConcertService;
import com.bottega.vendor.concert.fixtures.InMemoryConcertRepo;
import com.bottega.vendor.concert.fixtures.clients.ConcertApiClient;
import com.bottega.vendor.concert.fixtures.fixtures.ConcertBuilder;
import com.bottega.vendor.concert.infra.repo.ConcertRepo;
import com.bottega.vendor.fixtures.FakePricingClient;
import com.bottega.vendor.fixtures.SharedFixtures;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConcertFixtures {

    //SUTs
    @Autowired
    public ConcertService concertService;

    //infrastructure
    @Autowired
    public ConcertRepo concertRepo;

    //builders
    @Autowired
    public ConcertBuilder concertBuilder;

    //clients
    @Autowired
    public ConcertApiClient concertClient;

    public static ConcertFixtures init(SharedFixtures sharedFixtures) {
        ConcertFixtures concertFixtures = new ConcertFixtures();

        initInfrastructure(concertFixtures);
        initSut(concertFixtures, sharedFixtures);
        initBuilders(concertFixtures);

        return concertFixtures;
    }

    private static void initInfrastructure(ConcertFixtures concertFixtures) {
        concertFixtures.concertRepo = new InMemoryConcertRepo();
    }

    private static void initSut(ConcertFixtures concertFixtures, SharedFixtures sharedFixtures) {
        concertFixtures.concertService = new ConcertService(
                new ConcertFactory(),
                concertFixtures.concertRepo,
                new FakePricingClient(),
                sharedFixtures.fakeEventPublisher()
        );
    }

    private static void initBuilders(ConcertFixtures concertFixtures) {
        concertFixtures.concertBuilder = new ConcertBuilder(concertFixtures.concertRepo);
    }


    public void tearDown() {
        concertRepo.deleteAll();
    }


}
