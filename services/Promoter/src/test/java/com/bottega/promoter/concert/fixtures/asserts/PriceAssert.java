package com.bottega.promoter.concert.fixtures.asserts;

import com.bottega.promoter.concert.*;
import com.bottega.sharedlib.vo.Money;
import lombok.RequiredArgsConstructor;
import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class PriceAssert {

    private final Price price;

    public static PriceAssert assertThatPrice(Price price) {
        return new PriceAssert(price);
    }

    public PriceAssert equalTo(Money priceValue) {
        assertThat(price.getPrice()).isEqualTo(priceValue);
        return this;
    }

    public PriceAssert hasFactors(PriceFactor... factors) {
        assertThat(price.getFactors()).containsExactlyInAnyOrder(factors);
        return this;
    }
}
