package com.bottega.pricing.price.api.app;

import com.bottega.pricing.price.domain.ItemPrice;
import com.bottega.pricing.price.domain.PriceFactorFactory;
import com.bottega.pricing.price.domain.PricingEventFactory;
import com.bottega.pricing.price.infra.repo.ItemPriceRepo;
import com.bottega.sharedlib.ddd.ApplicationService;
import com.bottega.sharedlib.event.EventPublisher;
import com.bottega.sharedlib.vo.Money;
import com.bottega.sharedlib.vo.error.ErrorResult;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;

import java.util.List;

import static com.bottega.pricing.price.api.app.FactorErrorCode.item_not_found;
import static com.bottega.sharedlib.vo.error.ErrorResult.notFound;
import static io.vavr.control.Option.of;

@ApplicationService
@AllArgsConstructor
public class PriceService {

    private final ItemPriceRepo priceRepo;
    private final EventPublisher eventPublisher;


    public Either<ErrorResult, List<ItemPrice>> applyPercentageFactor(String itemId, int percentage) {

        List<ItemPrice> updatedPrices = priceRepo.findByItemId(itemId).stream()
                .map(itemPrice -> itemPrice.applyFactor(PriceFactorFactory.percentageFactor(percentage, itemPrice)))
                //TODO: outbox?
                .peek(itemPrice -> eventPublisher.publish(PricingEventFactory.priceChange(itemPrice)))
                .toList();
        priceRepo.saveAll(updatedPrices);

        return of(updatedPrices)
                .filter(itemPrices -> !itemPrices.isEmpty())
                .toEither(notFound(item_not_found, "No price entries found for requested item. itemId: " + itemId));
    }

    public ItemPrice addPrice(String itemId, Money price) {
    //TODO test
        throw new RuntimeException("not implemented yet");
//        return ItemPrice.create(itemId, price);
    }
}
