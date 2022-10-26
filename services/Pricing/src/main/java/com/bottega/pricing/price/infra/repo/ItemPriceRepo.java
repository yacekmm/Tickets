package com.bottega.pricing.price.infra.repo;

import com.bottega.pricing.price.domain.ItemPrice;
import com.bottega.pricing.price.domain.PriceId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemPriceRepo extends CrudRepository<ItemPrice, PriceId> {
    List<ItemPrice> findByItemId(String itemId);
}
