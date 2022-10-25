package com.bottega.vendor.concert.tests.asserts;

import com.bottega.sharedlib.vo.Money;
import com.bottega.vendor.concert.Price;
import com.bottega.vendor.concert.PriceFactor;
import lombok.RequiredArgsConstructor;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor(staticName = "assertThatPrice")
public class PriceAssert {

    private final Price price;

    public PriceAssert is(Money priceValue) {
        assertThat(price.price()).isEqualTo(priceValue);
        return this;
    }

    public PriceAssert hasFactors(PriceFactor... factors) {
        assertThat(price.factors()).containsExactlyInAnyOrder(factors);
        return this;
    }
}
