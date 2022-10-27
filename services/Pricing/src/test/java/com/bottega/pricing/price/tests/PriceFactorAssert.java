package com.bottega.pricing.price.tests;

import com.bottega.pricing.price.domain.ItemPrice;
import com.bottega.pricing.price.domain.PriceFactor;
import lombok.RequiredArgsConstructor;

import static com.bottega.pricing.price.domain.FactorType.PERCENTAGE;
import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor(staticName = "assertThatFactor")
public class PriceFactorAssert {

    private final PriceFactor factor;

    public PriceFactorAssert isPercentageFactor(int value, ItemPrice price) {
        assertThat(factor.getPrice()).isEqualTo(price);
        assertThat(factor.getFactorXXX().getType()).isEqualTo(PERCENTAGE);
        assertThat(factor.getFactorXXX().getValue()).isEqualTo(value);
        return this;
    }
}
