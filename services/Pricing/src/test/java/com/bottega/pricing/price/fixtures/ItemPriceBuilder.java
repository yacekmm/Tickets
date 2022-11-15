package com.bottega.pricing.price.fixtures;

import com.bottega.pricing.price.domain.*;
import com.bottega.sharedlib.fixtures.UUIDs;
import com.bottega.sharedlib.repo.MoneyDbEntity;
import com.bottega.sharedlib.vo.Money;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class ItemPriceBuilder {


    private int price = 200_00;

    public ItemPrice build() {
        return new ItemPrice(new PriceId(), MoneyDbEntity.from(new Money(price)), UUIDs.zeros(), new ArrayList<>());
    }

    public ItemPriceBuilder withPrice(int price) {
        this.price = price;
        return this;
    }
}
