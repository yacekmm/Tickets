package com.bottega.pricing.fixtures;

import com.bottega.pricing.price.api.app.PriceService;
import com.bottega.pricing.price.fixtures.*;
import com.bottega.pricing.price.infra.repo.ItemPriceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PriceFixtures {


    //SUTs
    @Autowired
    public PriceService priceService;

    //repos
    @Autowired
    public ItemPriceRepo priceRepo;

    //API clients
    @Autowired
    public PriceApiClient priceApiClient;

    public static PriceFixtures init(SharedFixtures sharedFixtures) {
        PriceFixtures priceFixtures = new PriceFixtures();

        initRepos(priceFixtures);
        initSut(priceFixtures, sharedFixtures);

        return priceFixtures;
    }

    private static void initRepos(PriceFixtures priceFixtures) {
        priceFixtures.priceRepo = new InMemoryPriceRepo();
    }

    private static void initSut(PriceFixtures factorFixtures, SharedFixtures sharedFixtures) {
        factorFixtures.priceService = new PriceService(factorFixtures.priceRepo, sharedFixtures.eventPublisher);
    }

    public void tearDown() {
        priceRepo.deleteAll();
    }

}
