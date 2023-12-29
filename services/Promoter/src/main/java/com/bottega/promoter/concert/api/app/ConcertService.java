package com.bottega.promoter.concert.api.app;

import com.bottega.promoter.agreements.PromoterAgreement;
import com.bottega.promoter.agreements.PromoterService;
import com.bottega.promoter.concert.Price;
import com.bottega.promoter.concert.domain.*;
import com.bottega.promoter.concert.infra.repo.ConcertRepo;
import com.bottega.promoter.infra.client.pricing.PricingClient;
import com.bottega.sharedlib.ddd.ApplicationService;
import com.bottega.sharedlib.event.EventPublisher;
import com.bottega.sharedlib.vo.error.ErrorResult;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;

import java.util.List;

import static com.bottega.promoter.concert.domain.PromoterEventFactory.concertCreated;
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
    private final PromoterService promoterService;

    public Either<ErrorResult, Concert> createConcert(String title, String dateTime, String promoterIdString) {
        PromoterAgreement promoterAgreement = promoterService.getPromoterAgreement(promoterIdString);
        if(promoterAgreement == null){
            return Either.left(notFound(not_found, "Promoter contract not found for %s", promoterIdString));
        }
        return concertFactory.createConcert(title, dateTime, promoterAgreement.promoterId())
                .peek(concert -> concert.initNewConcert(tagService, categoryService))
                .map(concertRepo::save)
                .peek(concert -> eventPublisher.publish(concertCreated(concert, promoterAgreement.profitMarginPercentage())));
    }

    public Either<ErrorResult, List<Price>> discountConcert(String concertId, int percentage) {
        return ofOptional(concertRepo.findById(new ConcertId(concertId)))
                .toEither(notFound(not_found, "Concert with given ID does not exist. ID: " + concertId))
                .flatMap(concert -> pricingClient.applyPercentageDiscount(concert.getId(), percentage));
    }
}
