package com.bottega.promoter.infra.client.pricing;

import java.util.*;

import com.bottega.promoter.concert.Price;
import com.bottega.promoter.concert.domain.ConcertId;
import com.bottega.sharedlib.annotations.ApiClient;
import com.bottega.sharedlib.vo.error.ErrorResult;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import static com.bottega.promoter.concert.api.app.ConcertErrorCode.http_error;
import static com.bottega.sharedlib.vo.error.ErrorResult.serviceUnavailable;
import static io.vavr.control.Option.ofOptional;

@ApiClient
@AllArgsConstructor
@Slf4j
public class HttpPricingClient implements PricingClient {

    private final WebClient pricingWebClient;


    @Override
    public Either<ErrorResult, List<Price>> applyPercentageDiscount(ConcertId itemId, int percentage) {
        return ofOptional(pricingWebClient
                .post()
                .uri("/api/v1/item/{itemId}/price-factor/percentage", itemId.asString())
                .bodyValue(new PercentagePriceFactorRequestDto(percentage))
                .retrieve()
                .bodyToMono(PriceResponseDto[].class)
                .blockOptional())
                .map(prices -> Arrays.stream(prices)
                        .map(PriceResponseDto::toPrice)
                        .toList())
                .toEither(serviceUnavailable(http_error, "Error applying discount in Pricing Service"));
    }
}
