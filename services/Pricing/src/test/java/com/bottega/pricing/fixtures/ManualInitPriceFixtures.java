package com.bottega.pricing.fixtures;

import com.bottega.pricing.initialPrice.InitPriceCalculator;
import com.bottega.pricing.initialPrice.InitialPriceService;
import com.bottega.pricing.price.api.app.PriceService;

import static org.mockito.Mockito.mock;

public class ManualInitPriceFixtures {


    //SUTs
    public InitialPriceService initPriceService;

    //mocks
    public PriceService priceService;

    public static ManualInitPriceFixtures init() {
        ManualInitPriceFixtures factorFixtures = new ManualInitPriceFixtures();

        initMocks(factorFixtures);
        initSut(factorFixtures);

        return factorFixtures;
    }

    private static void initMocks(ManualInitPriceFixtures factorFixtures) {
        factorFixtures.priceService = mock(PriceService.class);
    }

    private static void initSut(ManualInitPriceFixtures factorFixtures) {
        factorFixtures.initPriceService = new InitialPriceService(
                new InitPriceCalculator(),
                factorFixtures.priceService
        );
    }

}
