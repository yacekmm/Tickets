package com.bottega.vendor.infra.client.pricing;

import com.bottega.sharedlib.vo.error.ErrorResult;
import com.bottega.vendor.concert.Price;
import com.bottega.vendor.concert.domain.ConcertId;
import io.vavr.control.Either;

import java.util.List;

public interface PricingClient {
    Either<ErrorResult, List<Price>> applyPercentageDiscount(ConcertId itemId, int percentage);
}
