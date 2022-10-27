package com.bottega.pricing.price.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.bottega.pricing.price.domain.FactorType.PERCENTAGE;
import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor
public class PercentageFactor implements FactorXXX {

    private FactorType type;
    private int value;

    public static PercentageFactor init(int percentage) {
        return new PercentageFactor(PERCENTAGE, percentage);
    }
}
