package com.bottega.promoter.pricing.infra;

import com.bottega.promoter.concert.Price;
import com.bottega.promoter.concert.domain.ConcertId;
import com.bottega.sharedlib.vo.Money;

import java.util.*;

public class PriceRepo {

    Map<String, Price> db = new HashMap<>();

    public List<Price> findByItemId(ConcertId concertId) {
        return db.values().stream()
                .filter(price -> price.getItemId().equals(concertId.asString()))
                .toList();
    }

    public Price savePriceFor(String itemId, int priceValue) {
        Price price = new Price(UUID.randomUUID().toString(), itemId, new Money(priceValue), new ArrayList<>());
        db.put(price.getId(), price);
        return price;
    }

    public Price save(Price price) {
        db.put(price.getId(), price);
        return price;
    }
}
