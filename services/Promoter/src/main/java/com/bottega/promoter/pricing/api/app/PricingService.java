package com.bottega.promoter.pricing.api.app;

import com.bottega.promoter.concert.Price;
import com.bottega.promoter.concert.PriceFactor;
import com.bottega.promoter.concert.domain.ConcertId;
import com.bottega.promoter.pricing.infra.PriceRepo;
import com.bottega.sharedlib.vo.error.ErrorResult;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;

import java.util.List;

import static com.bottega.sharedlib.vo.error.ErrorResult.notFound;
import static com.bottega.sharedlib.vo.error.GenericErrorCode.not_found;
import static io.vavr.control.Either.left;

@AllArgsConstructor
public class PricingService {

    private PriceRepo priceRepo;

    public Either<ErrorResult, List<Price>> applyPercentageDiscount(ConcertId concertId, int percentage) {

        List<Price> updatedPrices = priceRepo.findByItemId(concertId).stream()
                .map(price -> price.applyFactor(new PriceFactor("PERCENTAGE", percentage, null)))
                .peek(priceRepo::save)
                .toList();

        if (updatedPrices.isEmpty()) {
            return left(notFound(not_found, "No price entries found for requested item. itemId: " + concertId));
        } else {
            return Either.right(updatedPrices);
        }
    }

}
