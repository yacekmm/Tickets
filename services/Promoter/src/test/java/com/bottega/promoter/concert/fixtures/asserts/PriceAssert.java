package com.bottega.promoter.concert.fixtures.asserts;

import com.bottega.promoter.concert.*;
import com.bottega.sharedlib.vo.Money;
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
