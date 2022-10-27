package com.bottega.pricing.price.fixtures;

import com.bottega.pricing.price.api.app.PriceService;
import com.bottega.pricing.price.infra.repo.ItemPriceRepo;

public class ManualPriceFixtures {


    //SUTs
    public PriceService priceService;

    //repos
    public ItemPriceRepo priceRepo;

    //builders
    public ItemPriceBuilder priceBuilder;


    public static ManualPriceFixtures init() {
        ManualPriceFixtures factorFixtures = new ManualPriceFixtures();

        initRepos(factorFixtures);
        initSut(factorFixtures);
        initBuilders(factorFixtures);

        return factorFixtures;
    }

    private static void initRepos(ManualPriceFixtures priceFixtures) {
        priceFixtures.priceRepo = new InMemoryPriceRepo();
    }

    private static void initSut(ManualPriceFixtures factorFixtures) {
        factorFixtures.priceService = new PriceService(factorFixtures.priceRepo);
    }

    private static void initBuilders(ManualPriceFixtures priceFixtures) {
        priceFixtures.priceBuilder = new ItemPriceBuilder(priceFixtures.priceRepo);
    }

}
