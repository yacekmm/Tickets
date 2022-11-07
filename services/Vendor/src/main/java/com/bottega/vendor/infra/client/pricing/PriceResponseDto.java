package com.bottega.vendor.infra.client.pricing;

import com.bottega.sharedlib.vo.Money;
import com.bottega.vendor.concert.*;

import java.util.*;

public record PriceResponseDto(
        int price,
        PriceFactorResponseDto[] factors) {

    public Price toPrice() {
        return new Price(
                new Money(price),
                Arrays.stream(factors)
                        .map(PriceFactorResponseDto::toPriceFactor)
                        .toList());
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
