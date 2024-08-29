package com.bottega.promoter.pricing.infra;

import com.bottega.promoter.concert.Price;
import com.bottega.promoter.concert.domain.ConcertId;
import com.bottega.sharedlib.vo.Money;

import java.util.ArrayList;
import java.util.List;

public class PriceRepo {
    public List<Price> findByItemId(ConcertId concertId) {
        return List.of(new Price(new Money(100_00), new ArrayList<>()));
    }

    public void save(Price price) {
        //who cares
    }
}
