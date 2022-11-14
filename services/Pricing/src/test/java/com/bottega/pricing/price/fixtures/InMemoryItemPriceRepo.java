package com.bottega.pricing.price.fixtures;

import com.bottega.pricing.price.domain.*;
import com.bottega.pricing.price.infra.repo.ItemPriceRepo;
import com.bottega.sharedlib.infra.repo.InMemoryRepo;

import java.util.List;

public class InMemoryItemPriceRepo
        extends InMemoryRepo<ItemPrice, PriceId>
        implements ItemPriceRepo {

    @Override
    public List<ItemPrice> findByItemId(String itemId) {
        return database.values().stream()
                .filter(itemPrice -> itemPrice.getItemId().equals(itemId))
                .toList();
    }
}
