package com.bottega.promoter.concert.fixtures.asserts;

import com.bottega.promoter.concert.Price;
import com.bottega.promoter.concert.PriceFactor;
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

    public PriceAssert hasPercentageFactor(int value) {
        assertThat(price.getFactors()).contains(new PriceFactor("PERCENTAGE", value, null));
        return this;
    }

    public PriceAssert equalTo(int priceValue) {
        return equalTo(new Money(priceValue));
    }
}
