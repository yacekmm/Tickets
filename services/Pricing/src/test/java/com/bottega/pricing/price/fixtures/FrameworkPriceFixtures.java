package com.bottega.pricing.price.fixtures;

import com.bottega.pricing.price.infra.repo.ItemPriceRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FrameworkPriceFixtures {

    public final ItemPriceBuilder priceBuilder;
    public final PriceApiClient factorClient;
    public final ItemPriceRepo priceRepo;

    public void tearDown() {
        priceRepo.deleteAll();
    }
}
