package com.bottega.pricing.price.infra.repo;

import com.bottega.pricing.price.domain.ItemPrice;
import com.bottega.sharedlib.repo.AggregateId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPriceRepo extends CrudRepository<ItemPrice, AggregateId> {
}
