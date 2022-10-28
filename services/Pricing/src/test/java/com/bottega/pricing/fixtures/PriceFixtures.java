package com.bottega.pricing.fixtures;

import com.bottega.pricing.price.api.app.PriceService;
import com.bottega.pricing.price.fixtures.InMemoryPriceRepo;
import com.bottega.pricing.price.fixtures.ItemPriceBuilder;
import com.bottega.pricing.price.fixtures.PriceApiClient;
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

    //builders
    @Autowired
    public ItemPriceBuilder priceBuilder;

    //API clients
    @Autowired
    public PriceApiClient priceApiClient;

    public static PriceFixtures init(SharedFixtures sharedFixtures) {
        PriceFixtures priceFixtures = new PriceFixtures();

        initRepos(priceFixtures);
        initSut(priceFixtures, sharedFixtures);
        initBuilders(priceFixtures);

        return priceFixtures;
    }

    private static void initRepos(PriceFixtures priceFixtures) {
        priceFixtures.priceRepo = new InMemoryPriceRepo();
    }

    private static void initSut(PriceFixtures factorFixtures, SharedFixtures sharedFixtures) {
        factorFixtures.priceService = new PriceService(factorFixtures.priceRepo, sharedFixtures.eventPublisher);
    }

    private static void initBuilders(PriceFixtures priceFixtures) {
        priceFixtures.priceBuilder = new ItemPriceBuilder(priceFixtures.priceRepo);
    }

    public void tearDown() {
        priceRepo.deleteAll();
    }

}
