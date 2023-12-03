package com.bottega.promoter.infra.client.pricing;

import java.util.*;

import com.bottega.promoter.concert.*;
import com.bottega.sharedlib.vo.Money;

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
            String type,
            int value,
            Map<String, String> params
    ) {
        public PriceFactor toPriceFactor() {
            return new PriceFactor(type, value, params);
        }
    }
}
