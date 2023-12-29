package com.bottega.pricing.price.fixtures;

import com.bottega.pricing.price.domain.*;
import com.bottega.pricing.price.infra.repo.ItemPriceRepo;
import com.bottega.sharedlib.repo.MoneyDbEntity;
import com.bottega.sharedlib.vo.Money;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class Builder {

    public final ItemPriceRepo itemPriceRepo;

    //default values
    private Money price = new Money(200_00);
    private String itemId = "mock-item-id";


    public ItemPrice build(){
        return new ItemPrice(new PriceId(), MoneyDbEntity.from(price), itemId, new ArrayList<>());
    }

    public ItemPrice inDb() {
        return itemPriceRepo.save(build());
    }

    public Builder priceForItem(int price, String itemId) {
        this.price = new Money(price);
        this.itemId = itemId;
        return this;
    }
}
