package com.bottega.vendor.concert.application.api;

import com.bottega.sharedlib.ddd.ApplicationService;
import com.bottega.sharedlib.vo.Money;
import com.bottega.sharedlib.vo.error.ErrorResult;
import com.bottega.vendor.concert.Price;
import com.bottega.vendor.concert.PriceFactor;
import com.bottega.vendor.concert.domain.Concert;
import com.bottega.vendor.concert.domain.ConcertFactory;
import com.bottega.vendor.concert.domain.ConcertId;
import com.bottega.vendor.concert.infra.repo.ConcertRepo;
import com.bottega.vendor.contract.VendorId;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;

import static com.bottega.sharedlib.vo.error.ErrorResult.notFound;
import static com.bottega.vendor.concert.application.api.dto.ConcertErrorCode.concert_not_found;
import static io.vavr.control.Option.ofOptional;

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

    public Either<ErrorResult, Price> discountConcert(String concertId, int percentage) {
        return ofOptional(concertRepo.findById(new ConcertId(concertId)))
                .toEither(notFound(concert_not_found, "Concert with given ID does not exist. ID: " + concertId))
                .map(concert -> new Price(Money.ZERO, new PriceFactor[]{}));
    }
}
