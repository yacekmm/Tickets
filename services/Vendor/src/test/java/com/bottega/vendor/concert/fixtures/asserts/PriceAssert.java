package com.bottega.vendor.concert.fixtures.asserts;

import com.bottega.sharedlib.vo.Money;
import com.bottega.vendor.concert.*;
import lombok.RequiredArgsConstructor;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor(staticName = "assertThatPrice")
public class PriceAssert {

    private final Price price;

    public PriceAssert equalTo(Money priceValue) {
        assertThat(price.getPrice()).isEqualTo(priceValue);
        return this;
    }

    public PriceAssert hasFactors(PriceFactor... factors) {
        assertThat(price.getFactors()).containsExactlyInAnyOrder(factors);
        return this;
    }
}
