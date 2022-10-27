package com.bottega.pricing.fixtures;

import com.bottega.pricing.price.api.app.PriceService;
import com.bottega.pricing.price.fixtures.InMemoryPriceRepo;
import com.bottega.pricing.price.fixtures.ItemPriceBuilder;
import com.bottega.pricing.price.infra.repo.ItemPriceRepo;

public class ManualPriceFixtures {


    //SUTs
    public PriceService priceService;

    //repos
    public ItemPriceRepo priceRepo;

    //builders
    public ItemPriceBuilder priceBuilder;

    //fixtures

    public static ManualPriceFixtures init(SharedFixtures sharedFixtures) {
        ManualPriceFixtures factorFixtures = new ManualPriceFixtures();

        initRepos(factorFixtures);
        initSut(factorFixtures, sharedFixtures);
        initBuilders(factorFixtures);

        return factorFixtures;
    }

    private static void initRepos(ManualPriceFixtures priceFixtures) {
        priceFixtures.priceRepo = new InMemoryPriceRepo();
    }

    private static void initSut(ManualPriceFixtures factorFixtures, SharedFixtures sharedFixtures) {
        factorFixtures.priceService = new PriceService(factorFixtures.priceRepo, sharedFixtures.eventPublisher);
    }

    private static void initBuilders(ManualPriceFixtures priceFixtures) {
        priceFixtures.priceBuilder = new ItemPriceBuilder(priceFixtures.priceRepo);
    }

}
