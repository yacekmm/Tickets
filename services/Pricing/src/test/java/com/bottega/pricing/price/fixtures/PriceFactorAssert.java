package com.bottega.pricing.price.fixtures;

import com.bottega.pricing.price.domain.*;
import lombok.RequiredArgsConstructor;
import static com.bottega.pricing.price.domain.FactorType.PERCENTAGE;
import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class PriceFactorAssert {

    private final PriceFactor factor;

    public static PriceFactorAssert assertThatFactor(PriceFactor factor) {
        return new PriceFactorAssert(factor);
    }

    public PriceFactorAssert isPercentageFactor(int value, ItemPrice price) {
        assertThat(factor.getPrice()).isEqualTo(price);
        assertThat(factor.getFactorPolicy().getType()).isEqualTo(PERCENTAGE);
        assertThat(factor.getFactorPolicy().getValue()).isEqualTo(value);
        return this;
    }
}
