package com.bottega.vendor.infra.client.pricing;

import com.bottega.sharedlib.annotations.ApiClient;
import com.bottega.sharedlib.vo.error.ErrorResult;
import com.bottega.vendor.concert.Price;
import com.bottega.vendor.concert.domain.ConcertId;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

import static com.bottega.sharedlib.vo.error.ErrorResult.serviceUnavailable;
import static com.bottega.vendor.concert.api.app.ConcertErrorCode.http_error;
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
