package com.bottega.pricing.price.domain;

import com.bottega.sharedlib.ddd.DomainFactory;

@DomainFactory
public class PriceFactorFactory {
    public static PriceFactor percentageFactor(int percentage, ItemPrice price) {
        return new PriceFactor(new FactorId(), price, PercentageFactor.init(percentage));
    }
}
