package com.bottega.pricing.price.api.app;

import java.util.List;

import com.bottega.pricing.price.domain.ItemPrice;
import com.bottega.pricing.price.infra.repo.ItemPriceRepo;
import com.bottega.pricing.priceRead.api.app.PriceUpdateService;
import com.bottega.sharedlib.ddd.ApplicationService;
import com.bottega.sharedlib.event.EventPublisher;
import com.bottega.sharedlib.vo.Money;
import com.bottega.sharedlib.vo.error.ErrorResult;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import static com.bottega.pricing.price.domain.PriceFactorFactory.percentageFactor;
import static com.bottega.pricing.price.domain.PricingEventFactory.priceChange;
import static com.bottega.sharedlib.vo.error.ErrorResult.notFound;
import static com.bottega.sharedlib.vo.error.GenericErrorCode.not_found;
import static io.vavr.control.Either.left;

@ApplicationService
@AllArgsConstructor
public class PriceService {

    private final ItemPriceRepo priceRepo;
    private final EventPublisher eventPublisher;
    private final PriceUpdateService priceUpdateService;

    @Transactional
    public Either<ErrorResult, List<ItemPrice>> applyPercentageFactor(String itemId, int percentage) {

        List<ItemPrice> updatedPrices = priceRepo.findByItemId(itemId).stream()
                .map(itemPrice -> itemPrice.applyFactor(percentageFactor(percentage, itemPrice)))
                //TODO save in repo
                .peek(priceUpdateService::updateReadModel)
                .peek(itemPrice -> eventPublisher.publish(priceChange(itemPrice)))
                .toList();

        if (updatedPrices.isEmpty()) {
            return left(notFound(not_found, "No price entries found for requested item. itemId: " + itemId));
        } else {
            return Either.right(updatedPrices);
        }
    }

    public Either<ErrorResult, ItemPrice> addNewPrice(String itemId, Money price) {
        ItemPrice itemPrice = priceRepo.save(ItemPrice.create(itemId, price));
        eventPublisher.publish(priceChange(itemPrice));
        return Either.right(itemPrice);
    }
}
