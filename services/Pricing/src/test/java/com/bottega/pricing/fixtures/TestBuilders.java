package com.bottega.pricing.fixtures;

import com.bottega.pricing.price.fixtures.ItemPriceBuilder;
import com.bottega.pricing.price.infra.repo.ItemPriceRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TestBuilders {

    private ItemPriceRepo priceRepo;

    public ItemPriceBuilder aPrice() {
        return new ItemPriceBuilder(priceRepo);
    }

}
