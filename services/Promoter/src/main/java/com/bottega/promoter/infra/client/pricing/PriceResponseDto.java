package com.bottega.promoter.infra.client.pricing;

import com.bottega.promoter.concert.Price;
import com.bottega.promoter.concert.PriceFactor;
import com.bottega.sharedlib.vo.Money;

import java.util.Arrays;
import java.util.Map;

public record PriceResponseDto(
        int price,
        PriceFactorResponseDto[] factors) {

    public Price toPrice() {
        return new Price(
                "fake-id",
                "item-id",
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
