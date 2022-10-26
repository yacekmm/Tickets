package com.bottega.vendor.infra.client.pricing;

import com.bottega.sharedlib.vo.Money;
import com.bottega.vendor.concert.Price;
import com.bottega.vendor.concert.PriceFactor;

import java.util.Arrays;
import java.util.Map;

public record PriceResponseDto(
        int price,
        PriceFactorResponseDto[] factors) {

    public Price toPrice() {
        return new Price(
                new Money(price),
                Arrays.stream(factors)
                        .map(PriceFactorResponseDto::toPriceFactor)
                        .toList()
                        .toArray(PriceFactor[]::new));
    }

    record PriceFactorResponseDto(
            String name,
            int value,
            Map<String, String> params
    ) {
        public PriceFactor toPriceFactor() {
            return new PriceFactor(name, value, params);
        }
    }
}
