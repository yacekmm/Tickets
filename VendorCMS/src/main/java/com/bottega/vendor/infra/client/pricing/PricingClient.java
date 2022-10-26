package com.bottega.vendor.infra.client.pricing;

import com.bottega.sharedlib.vo.error.ErrorResult;
import com.bottega.vendor.concert.Price;
import com.bottega.vendor.concert.domain.ConcertId;
import io.vavr.control.Either;

public interface PricingClient {
    Either<ErrorResult, Price> applyPercentageDiscount(ConcertId itemId, int percentage);
}
