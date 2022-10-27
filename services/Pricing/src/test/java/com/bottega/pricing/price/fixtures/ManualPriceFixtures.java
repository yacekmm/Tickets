package com.bottega.pricing.price.fixtures;

import com.bottega.pricing.fixtures.SharedFixtures;
import com.bottega.pricing.price.api.app.PriceService;
import com.bottega.pricing.price.infra.repo.ItemPriceRepo;

public class ManualPriceFixtures {


    //SUTs
    public PriceService priceService;

    //repos
    public ItemPriceRepo priceRepo;

    //builders
    public ItemPriceBuilder priceBuilder;

    //fixtures
    public SharedFixtures sharedFixtures;

    public static ManualPriceFixtures init(SharedFixtures sharedFixtures) {
        ManualPriceFixtures factorFixtures = new ManualPriceFixtures();

        factorFixtures.sharedFixtures = sharedFixtures;
        initRepos(factorFixtures);
        initSut(factorFixtures);
        initBuilders(factorFixtures);

        return factorFixtures;
    }

    private static void initRepos(ManualPriceFixtures priceFixtures) {
        priceFixtures.priceRepo = new InMemoryPriceRepo();
    }

    private static void initSut(ManualPriceFixtures factorFixtures) {
        factorFixtures.priceService = new PriceService(factorFixtures.priceRepo, factorFixtures.sharedFixtures.eventPublisher);
    }

    private static void initBuilders(ManualPriceFixtures priceFixtures) {
        priceFixtures.priceBuilder = new ItemPriceBuilder(priceFixtures.priceRepo);
    }

}
