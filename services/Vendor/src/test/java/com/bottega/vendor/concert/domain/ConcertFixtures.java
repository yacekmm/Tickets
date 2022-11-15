package com.bottega.vendor.concert.domain;

import com.bottega.vendor.agreements.VendorService;
import com.bottega.vendor.concert.api.app.ConcertService;
import com.bottega.vendor.concert.fixtures.*;
import com.bottega.vendor.concert.fixtures.clients.ConcertHttpClient;
import com.bottega.vendor.concert.infra.repo.*;
import com.bottega.vendor.fixtures.*;
import com.bottega.vendor.infra.client.pricing.PricingClient;
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
    public VendorService vendorService;

    public static ConcertFixtures init(SharedFixtures sharedFixtures) {
        ConcertFixtures concertFixtures = new ConcertFixtures();

        initInfrastructure(concertFixtures);
        initMocks(concertFixtures);
        initServices(concertFixtures);
        initSut(concertFixtures, sharedFixtures);

        return concertFixtures;
    }

    private static void initInfrastructure(ConcertFixtures concertFixtures) {
        concertFixtures.concertRepo = new InMemoryConcertRepo();
        concertFixtures.categoryRepo = new InMemoryCategoryRepo();
        concertFixtures.tagRepo = new InMemoryTagRepo();
    }

    private static void initMocks(ConcertFixtures concertFixtures) {
        concertFixtures.vendorService = Mockito.mock(VendorService.class);
    }

    private static void initServices(ConcertFixtures concertFixtures) {
        concertFixtures.tagService = new TagService();
        concertFixtures.categoryService = new CategoryService();
    }

    private static void initSut(ConcertFixtures concertFixtures, SharedFixtures sharedFixtures) {
        concertFixtures.concertService = new ConcertService(
                new ConcertFactory(sharedFixtures.clock),
                concertFixtures.concertRepo,
                new FakePricingClient(),
                sharedFixtures.fakeEventPublisher(),
                concertFixtures.tagService,
                concertFixtures.categoryService,
                concertFixtures.vendorService
        );
    }

    public void tearDown() {
        concertRepo.deleteAll();
        categoryRepo.deleteAll();
        tagRepo.deleteAll();
    }


}
