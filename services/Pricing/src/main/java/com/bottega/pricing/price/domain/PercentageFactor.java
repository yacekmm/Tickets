package com.bottega.pricing.price.domain;

import com.bottega.sharedlib.vo.Money;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.bottega.pricing.price.domain.FactorType.PERCENTAGE;
import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor
public class PercentageFactor implements FactorPolicy {

    private FactorType type;
    private int value;

    public static PercentageFactor init(int percentage) {
        return new PercentageFactor(PERCENTAGE, percentage);
    }

    @Override
    public Money applyToPrice(Money price) {
        return price.percentage(100 - value);
    }

}
