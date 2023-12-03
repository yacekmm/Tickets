package com.bottega.promoter.concert.api.rest;

import java.util.List;

import com.bottega.promoter.concert.api.app.ConcertService;
import com.bottega.sharedlib.dto.AggregateIdDto;
import com.bottega.sharedlib.vo.error.ErrorResult;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
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

        return concertService.createConcert(requestDto.title(), requestDto.date(), requestDto.promoterId())
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
