package com.bottega.promoter.concert.domain;

import com.bottega.promoter.agreements.PromoterService;
import com.bottega.promoter.concert.api.app.ConcertService;
import com.bottega.promoter.concert.fixtures.*;
import com.bottega.promoter.concert.fixtures.clients.ConcertHttpClient;
import com.bottega.promoter.concert.infra.repo.*;
import com.bottega.promoter.fixtures.*;
import com.bottega.promoter.infra.client.pricing.PricingClient;
import org.mockito.Mockito;
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
    @Autowired
    public CategoryRepo categoryRepo;
    @Autowired
    public TagRepo tagRepo;

    //clients
    @Autowired
    public ConcertHttpClient concertHttpClient;
    @Autowired
    public PricingClient pricingClient;

    //services
    @Autowired
    public TagService tagService;
    @Autowired
    public CategoryService categoryService;

    //mocks
    @Autowired
    public PromoterService promoterService;

    public static ConcertFixtures init(SharedFixtures sharedFixtures) {
        ConcertFixtures concertFixtures = new ConcertFixtures();

        initInfrastructure(concertFixtures);
        initClients(concertFixtures);
        initMocks(concertFixtures);
        initServices(concertFixtures);
        initSut(concertFixtures, sharedFixtures);

        return concertFixtures;
    }

    private static void initInfrastructure(ConcertFixtures concertFixtures) {
        //TODO initialize repo
        concertFixtures.concertRepo = null;
        concertFixtures.categoryRepo = new InMemoryCategoryRepo();
        concertFixtures.tagRepo = new InMemoryTagRepo();
    }

    private static void initClients(ConcertFixtures concertFixtures) {
        concertFixtures.pricingClient = new FakePricingClient();
    }

    private static void initMocks(ConcertFixtures concertFixtures) {
        concertFixtures.promoterService = Mockito.mock(PromoterService.class);
    }

    private static void initServices(ConcertFixtures concertFixtures) {
        concertFixtures.tagService = new TagService();
        concertFixtures.categoryService = new CategoryService();
    }

    private static void initSut(ConcertFixtures concertFixtures, SharedFixtures sharedFixtures) {
        concertFixtures.concertService = new ConcertService(
                new ConcertFactory(sharedFixtures.clock),
                concertFixtures.concertRepo,
                concertFixtures.pricingClient,
                sharedFixtures.fakeEventPublisher(),
                concertFixtures.tagService,
                concertFixtures.categoryService,
                concertFixtures.promoterService
        );
    }

    public void tearDown() {
        concertRepo.deleteAll();
        categoryRepo.deleteAll();
        tagRepo.deleteAll();
    }


}
