package com.bottega.vendor.concert.api;

import com.bottega.vendor.concert.application.api.ConcertService;
import com.bottega.vendor.config.ApiVersions;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.bottega.vendor.concert.api.AggregateIdDto.fromAggregateId;

@RestController
@RequestMapping(ApiVersions.V1 + "/concert" )
@AllArgsConstructor
public class ConcertRestController {


    ConcertService concertService;

    @PostMapping
    public ResponseEntity<AggregateIdDto> createConcert(
            @RequestBody CreateConcertRequestDto requestDto) {

        return concertService.createConcert(requestDto.title(), requestDto.dateTime(), requestDto.vendorId())
                .map(concert -> ResponseEntity.ok(fromAggregateId(concert.getId())))
                //TODO/JM: test exceptions
                .getOrElseThrow(() -> new RuntimeException("oh..."));
    }
}
