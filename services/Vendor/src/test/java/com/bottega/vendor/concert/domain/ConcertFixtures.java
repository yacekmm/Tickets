package com.bottega.vendor.concert.domain;

import com.bottega.vendor.concert.api.app.ConcertService;
import com.bottega.vendor.concert.fixtures.*;
import com.bottega.vendor.concert.fixtures.clients.ConcertApiClient;
import com.bottega.vendor.concert.infra.repo.ConcertRepo;
import com.bottega.vendor.fixtures.*;
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

    //services
    @Autowired
    public TagService tagService;

    public static ConcertFixtures init(SharedFixtures sharedFixtures) {
        ConcertFixtures concertFixtures = new ConcertFixtures();

        initInfrastructure(concertFixtures);
        initSut(concertFixtures, sharedFixtures);
        initBuilders(concertFixtures, sharedFixtures);

        return concertFixtures;
    }

    private static void initInfrastructure(ConcertFixtures concertFixtures) {
        concertFixtures.concertRepo = new InMemoryConcertRepo();
    }

    private static void initSut(ConcertFixtures concertFixtures, SharedFixtures sharedFixtures) {
        concertFixtures.tagService = new TagService();
        concertFixtures.concertService = new ConcertService(
                new ConcertFactory(sharedFixtures.clock),
                concertFixtures.concertRepo,
                new FakePricingClient(),
                sharedFixtures.fakeEventPublisher(),
                concertFixtures.tagService
        );
    }

    private static void initBuilders(ConcertFixtures concertFixtures, SharedFixtures sharedFixtures) {
        concertFixtures.concertBuilder = ConcertBuilder.init(concertFixtures.concertRepo, sharedFixtures.clock);
    }


    public void tearDown() {
        concertRepo.deleteAll();
    }


}
