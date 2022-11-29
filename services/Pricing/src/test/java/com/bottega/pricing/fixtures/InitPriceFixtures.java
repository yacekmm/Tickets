package com.bottega.pricing.fixtures;

import com.bottega.pricing.initialPrice.*;
import com.bottega.pricing.price.api.app.PriceService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.mockito.Mockito.mock;

@Component
@NoArgsConstructor
public class InitPriceFixtures {


    //SUTs
    @Autowired
    public InitialPriceService initPriceService;

    //mocks
    @Autowired
    public PriceService priceService;

    @Autowired
    public InitPriceChangeEventPublisher initPriceChangeEventPublisher;

    public static InitPriceFixtures init() {
        InitPriceFixtures initPriceFixtures = new InitPriceFixtures();

        initMocks(initPriceFixtures);
        initSut(initPriceFixtures);

        return initPriceFixtures;
    }

    private static void initMocks(InitPriceFixtures factorFixtures) {
        factorFixtures.priceService = mock(PriceService.class);
    }

    private static void initSut(InitPriceFixtures initPriceFixtures) {
        initPriceFixtures.initPriceService = new InitialPriceService(
                new InitPriceCalculator(),
                initPriceFixtures.priceService
        );
    }

}
