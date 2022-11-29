package com.bottega.pricing.initialPrice;

import com.bottega.sharedlib.vo.Money;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class InitPriceCalculator {

    private static final int BASE_PRICE = 100_00;

    public Money calcInitialPrice(int margin, Set<String> tags) {
        //complex price algorithm
        Money basePrice = new Money(BASE_PRICE);
        return basePrice.add(basePrice.percentage(margin));
    }
}
