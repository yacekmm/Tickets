package com.bottega.pricing.fixtures;

import com.bottega.pricing.price.api.app.PriceService;
import com.bottega.pricing.price.fixtures.*;
import com.bottega.pricing.price.infra.repo.ItemPriceRepo;
import com.bottega.pricing.priceRead.api.app.PriceUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.mockito.Mockito.mock;

@Component
public class PriceFixtures {

    //SUTs
    @Autowired
    public PriceService priceService;

    //repos
    @Autowired
    public ItemPriceRepo itemPriceRepo;

    //API clients
    @Autowired
    public PricingHttpClient pricingHttpClient;

    //mocks
    @Autowired
    public PriceUpdateService priceUpdateService;

    public static PriceFixtures init(SharedFixtures sharedFixtures) {
        PriceFixtures priceFixtures = new PriceFixtures();

        initMocks(priceFixtures);
        initRepos(priceFixtures);
        initSut(priceFixtures, sharedFixtures);

        return priceFixtures;
    }

    private static void initMocks(PriceFixtures priceFixtures) {
        priceFixtures.priceUpdateService = mock(PriceUpdateService.class);
    }

    private static void initRepos(PriceFixtures priceFixtures) {
        priceFixtures.itemPriceRepo = new InMemoryItemPriceRepo();
    }

    private static void initSut(PriceFixtures priceFixtures, SharedFixtures sharedFixtures) {
        priceFixtures.priceService = new PriceService(
                priceFixtures.itemPriceRepo,
                sharedFixtures.eventPublisher,
                priceFixtures.priceUpdateService
                );
    }

    public void tearDown() {
        itemPriceRepo.deleteAll();
    }

}
