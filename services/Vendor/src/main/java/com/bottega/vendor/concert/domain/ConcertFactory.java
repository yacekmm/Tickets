package com.bottega.vendor.concert.domain;

import com.bottega.sharedlib.ddd.DomainFactory;
import com.bottega.sharedlib.vo.error.ErrorResult;
import com.bottega.vendor.agreements.VendorId;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;

import java.time.Clock;
import java.util.HashSet;

@DomainFactory
@AllArgsConstructor
public class ConcertFactory {

    private final Clock clock;

    public Either<ErrorResult, Concert> createConcert(String title, String date, VendorId vendorId) {
        return ConcertDate.from(date, clock)
                .toEither()
                .map(concertDate -> new Concert(
                        new ConcertId(),
                        Title.from(title),
                        concertDate,
                        vendorId.asString(),
                        new HashSet<>(),
                        null));
    }
}
