package com.bottega.vendor.concert.api.app;

import com.bottega.sharedlib.ddd.ApplicationService;
import com.bottega.sharedlib.event.EventPublisher;
import com.bottega.sharedlib.vo.error.ErrorResult;
import com.bottega.vendor.agreements.*;
import com.bottega.vendor.concert.Price;
import com.bottega.vendor.concert.domain.*;
import com.bottega.vendor.concert.infra.repo.ConcertRepo;
import com.bottega.vendor.infra.client.pricing.PricingClient;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;

import java.util.List;

import static com.bottega.sharedlib.vo.error.ErrorResult.notFound;
import static com.bottega.sharedlib.vo.error.GenericErrorCode.not_found;
import static io.vavr.control.Option.ofOptional;

@ApplicationService
@AllArgsConstructor
public class ConcertService {

    private final ConcertFactory concertFactory;
    private final ConcertRepo concertRepo;
    private final PricingClient pricingClient;
    private final EventPublisher eventPublisher;
    private final TagService tagService;
    private final CategoryService categoryService;
    private final VendorService vendorService;

    public Either<ErrorResult, Concert> createConcert(String title, String dateTime, String vendorIdString) {
        VendorAgreement vendorAgreement = vendorService.getVendorAgreement(vendorIdString);
        if(vendorAgreement == null){
            return Either.left(notFound(not_found, "Vendor contract not found for %s", vendorIdString));
        }
        return concertFactory.createConcert(title, dateTime, vendorAgreement.vendorId())
                .peek(concert -> concert.initNewConcert(tagService, categoryService))
                .peek(concertRepo::save);
                //TODO: save in repo
                //TODO: publish event
//                .peek(concert -> eventPublisher.publish(concertCreated(concert, 10)));
    }

    public Either<ErrorResult, List<Price>> discountConcert(String concertId, int percentage) {
        return ofOptional(concertRepo.findById(new ConcertId(concertId)))
                .toEither(notFound(not_found, "Concert with given ID does not exist. ID: " + concertId))
                .flatMap(concert -> pricingClient.applyPercentageDiscount(concert.getId(), percentage));
    }
}
