package com.bottega.vendor.concert.api.rest;

import com.bottega.sharedlib.dto.AggregateIdDto;
import com.bottega.sharedlib.vo.error.ErrorResult;
import com.bottega.vendor.concert.api.app.ConcertService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bottega.sharedlib.config.ApiVersions.V1;
import static com.bottega.sharedlib.dto.AggregateIdDto.fromAggregateId;

@RestController
@AllArgsConstructor
public class ConcertRestController {

    private final ConcertService concertService;

    @PostMapping(path = V1 + "/concert")
    @ResponseBody
    public AggregateIdDto createConcert(
            @RequestBody CreateConcertRequestDto requestDto) {

        return concertService.createConcert(requestDto.title(), requestDto.date(), requestDto.vendorId())
                .map(concert -> fromAggregateId(concert.getId()))
                .getOrElseThrow(ErrorResult::toException);
    }

    @PostMapping(path = V1 + "/concert/{concertId}/discount")
    @ResponseBody
    public List<DiscountedPriceResponseDto> discountConcert(
            @PathVariable("concertId") String concertId,
            @RequestBody DiscountConcertRequestDto requestDto) {

        return concertService.discountConcert(concertId, requestDto.percentage())
                .map(prices -> prices.stream()
                        .map(DiscountedPriceResponseDto::fromPrice)
                        .toList())
                .getOrElseThrow(ErrorResult::toException);
    }
}
