package com.bottega.vendor.concert.application.api;

import com.bottega.sharedlib.ddd.ApplicationService;
import com.bottega.sharedlib.error.ErrorResult;
import com.bottega.vendor.concert.domain.Concert;
import com.bottega.vendor.concert.domain.ConcertFactory;
import com.bottega.vendor.concert.infra.repo.ConcertRepo;
import com.bottega.vendor.contract.VendorId;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;

//TODO: create a library with DDD annotations, reused in all services
@ApplicationService
@AllArgsConstructor
public class ConcertService {

    private final ConcertFactory concertFactory;
    private final ConcertRepo concertRepo;

    public Either<ErrorResult, Concert> createConcert(String title, String dateTime, String vendorIdString) {
        //TODO: should be retrieved from Vendor module
        VendorId vendorId = new VendorId(vendorIdString);
        return concertFactory.createConcert(title, dateTime, vendorId)
                        .peek(concertRepo::save);
    }
}
