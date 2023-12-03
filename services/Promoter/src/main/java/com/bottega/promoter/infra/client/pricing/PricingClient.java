package com.bottega.promoter.infra.client.pricing;

import java.util.List;

import com.bottega.promoter.concert.Price;
import com.bottega.promoter.concert.domain.ConcertId;
import com.bottega.sharedlib.vo.error.ErrorResult;
import io.vavr.control.Either;

public interface PricingClient {
    Either<ErrorResult, List<Price>> applyPercentageDiscount(ConcertId itemId, int percentage);
}
