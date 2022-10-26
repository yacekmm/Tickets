package com.bottega.vendor.concert.api;

import com.bottega.sharedlib.vo.error.ErrorResult;
import com.bottega.vendor.concert.application.api.ConcertService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.bottega.vendor.concert.api.AggregateIdDto.fromAggregateId;
import static com.bottega.vendor.config.ApiVersions.V1;

@RestController
@AllArgsConstructor
public class ConcertRestController {

    private final ConcertService concertService;

    @PostMapping(path = V1 + "/concert")
    @ResponseBody
    public AggregateIdDto createConcert(
            @RequestBody CreateConcertRequestDto requestDto) {

        return concertService.createConcert(requestDto.title(), requestDto.dateTime(), requestDto.vendorId())
                .map(concert -> fromAggregateId(concert.getId()))
                .getOrElseThrow(ErrorResult::toException);
    }

    @PostMapping(path = V1 + "/concert/{concertId}/discount")
    @ResponseBody
    public DiscountedPriceResponseDto discountConcert(
            @PathVariable("concertId") String concertId,
            @RequestBody DiscountConcertRequestDto requestDto) {

        return concertService.discountConcert(concertId, requestDto.percentage())
                .map(price -> new DiscountedPriceResponseDto(price.price().toInt()))
                .getOrElseThrow(ErrorResult::toException);
    }
}
