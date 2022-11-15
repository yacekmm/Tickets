package com.bottega.pricing.priceRead.api.app;

import com.bottega.pricing.price.domain.ItemPrice;
import com.bottega.sharedlib.ddd.ApplicationService;
import lombok.extern.slf4j.Slf4j;

@ApplicationService
@Slf4j
public class PriceUpdateService {


    public void updateReadModel(ItemPrice itemPrice) {
        log.info("will update read model with price: {}", itemPrice);
    }
}
