package com.bottega.pricing.initialPrice;

import com.bottega.pricing.price.api.app.PriceService;
import com.bottega.pricing.price.domain.ItemPrice;
import com.bottega.sharedlib.vo.Money;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@AllArgsConstructor
public class InitialPriceService {

    private final InitPriceCalculator calculator;
    private final PriceService priceService;

    public ItemPrice settleInitialPrice(String itemId, int margin, Set<String> tags) {
        Money price = calculator.calcInitialPrice(margin, tags);
        return priceService.addPrice(itemId, price);
    }

}
