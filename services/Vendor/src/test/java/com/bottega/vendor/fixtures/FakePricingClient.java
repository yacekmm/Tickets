package com.bottega.vendor.fixtures;

import com.bottega.sharedlib.vo.error.ErrorResult;
import com.bottega.vendor.concert.Price;
import com.bottega.vendor.concert.domain.ConcertId;
import com.bottega.vendor.infra.client.pricing.PricingClient;
import io.vavr.control.Either;

import java.util.List;

public class FakePricingClient implements PricingClient {

    @Override
    public Either<ErrorResult, List<Price>> applyPercentageDiscount(ConcertId itemId, int percentage) {
        return null;
    }
}
