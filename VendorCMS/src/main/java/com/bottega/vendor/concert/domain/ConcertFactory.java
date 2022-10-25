package com.bottega.vendor.concert.domain;

import com.bottega.sharedlib.ddd.DomainFactory;
import com.bottega.sharedlib.error.ErrorResult;
import com.bottega.vendor.contract.VendorId;
import io.vavr.control.Either;

@DomainFactory
public class ConcertFactory {

    public Either<ErrorResult, Concert> createConcert(String title, String date, VendorId vendorId) {
        return Either.right(new Concert(
                new ConcertId(),
                Title.from(title),
                ConcertDate.from(date),
                vendorId.asString()));
    }
}
