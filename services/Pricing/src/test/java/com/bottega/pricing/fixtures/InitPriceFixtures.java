package com.bottega.pricing.fixtures;

import com.bottega.pricing.initialPrice.InitPriceCalculator;
import com.bottega.pricing.initialPrice.InitialPriceService;
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
    public PriceService priceService;

    @Autowired
    public InitPriceChangeEventApiClient initPriceEventClient;

    public static InitPriceFixtures init(SharedFixtures sharedFixtures) {
        InitPriceFixtures initPriceFixtures = new InitPriceFixtures();

        initMocks(initPriceFixtures);
        initSut(initPriceFixtures, sharedFixtures);

        return initPriceFixtures;
    }

    private static void initMocks(InitPriceFixtures factorFixtures) {
        factorFixtures.priceService = mock(PriceService.class);
    }

    private static void initSut(InitPriceFixtures initPriceFixtures, SharedFixtures sharedFixtures) {
        initPriceFixtures.initPriceService = new InitialPriceService(
                new InitPriceCalculator(),
                initPriceFixtures.priceService,
                sharedFixtures.eventPublisher
        );
    }

}
