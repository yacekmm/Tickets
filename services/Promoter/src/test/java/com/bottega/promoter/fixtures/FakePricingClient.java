package com.bottega.promoter.fixtures;

import com.bottega.promoter.concert.Price;
import com.bottega.promoter.concert.PriceFactor;
import com.bottega.promoter.concert.domain.ConcertId;
import com.bottega.promoter.infra.client.pricing.PricingClient;
import com.bottega.sharedlib.vo.Money;
import com.bottega.sharedlib.vo.error.ErrorResult;
import io.vavr.control.Either;

import java.util.List;

import static java.util.List.of;

public class FakePricingClient implements PricingClient {

    @Override
    public Either<ErrorResult, List<Price>> applyPercentageDiscount(ConcertId itemId, int percentage) {
        return Either.right(
                of(new Price(
                        "fakeId",
                        "fakeItemId",
                        new Money(90_00),
                        of(new PriceFactor(
                                "PERCENTAGE",
                                percentage,
                                null))))
        );
    }
}
