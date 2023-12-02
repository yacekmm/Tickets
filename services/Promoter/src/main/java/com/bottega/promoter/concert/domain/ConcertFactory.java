package com.bottega.promoter.concert.domain;

import java.time.Clock;
import java.util.HashSet;

import com.bottega.promoter.agreements.PromoterId;
import com.bottega.sharedlib.ddd.DomainFactory;
import com.bottega.sharedlib.vo.error.ErrorResult;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import static com.bottega.sharedlib.vo.error.ErrorResult.badRequest;
import static com.bottega.sharedlib.vo.error.GenericErrorCode.invalid_request;
import static java.util.stream.Collectors.joining;

@DomainFactory
@AllArgsConstructor
public class ConcertFactory {

    private final Clock clock;

    public Either<ErrorResult, Concert> createConcert(String stringTitle, String date, PromoterId promoterId) {
        return ConcertDate.from(date, clock)
                .combine(Title.from(stringTitle))
                .ap((concertDate, title) -> new Concert(
                        new ConcertId(),
                        title,
                        concertDate,
                        promoterId.asString(),
                        new HashSet<>(),
                        null))
                .mapError(errorResults -> badRequest(
                        invalid_request,
                        errorResults.toJavaStream()
                                .map(errorResult -> errorResult.getCode().toString())
                                .collect(joining(","))))
                .toEither();
    }
}
