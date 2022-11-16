package com.bottega.vendor.fixtures;

import com.bottega.sharedlib.vo.Money;
import com.bottega.sharedlib.vo.error.ErrorResult;
import com.bottega.vendor.concert.*;
import com.bottega.vendor.concert.domain.ConcertId;
import com.bottega.vendor.infra.client.pricing.PricingClient;
import io.vavr.control.Either;

import java.util.List;

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
