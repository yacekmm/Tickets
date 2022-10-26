package com.bottega.vendor.infra.client.pricing;

import com.bottega.sharedlib.vo.Money;
import com.bottega.sharedlib.vo.error.ErrorResult;
import com.bottega.vendor.concert.Price;
import com.bottega.vendor.concert.PriceFactor;
import com.bottega.vendor.concert.domain.ConcertId;
import io.vavr.control.Either;
import org.apache.groovy.util.Maps;

public class FakeConcertClient implements PricingClient {
    @Override
    public Either<ErrorResult, Price> applyPercentageDiscount(ConcertId itemId, int percentage) {
        return Either.right(
                new Price(
                        new Money(100_00),
                        new PriceFactor(
                                "PERCENTAGE",
                                percentage,
                                Maps.of("type", "MINUS"))));
    }
}
