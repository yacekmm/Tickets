package com.bottega.promoter.fixtures;

import java.util.List;

import com.bottega.promoter.concert.*;
import com.bottega.promoter.concert.domain.ConcertId;
import com.bottega.promoter.infra.client.pricing.PricingClient;
import com.bottega.sharedlib.vo.Money;
import com.bottega.sharedlib.vo.error.ErrorResult;
import io.vavr.control.Either;
import static java.util.List.of;

public class FakePricingClient implements PricingClient {

    @Override
    public Either<ErrorResult, List<Price>> applyPercentageDiscount(ConcertId itemId, int percentage) {
        return Either.right(
                of(new Price(
                        new Money(90_00),
                        of(new PriceFactor(
                                "PERCENTAGE",
                                percentage,
                                null))))
        );
    }
}
