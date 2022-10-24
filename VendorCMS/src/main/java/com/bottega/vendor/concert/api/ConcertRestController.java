package com.bottega.vendor.concert.api;

import com.bottega.vendor.concert.application.api.ConcertService;
import com.bottega.vendor.concert.domain.ConcertId;
import com.bottega.vendor.config.ApiVersions;
import com.bottega.vendor.contract.VendorId;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiVersions.V1 + "/concert" )
@AllArgsConstructor
public class ConcertRestController {


    ConcertService concertService;

    @PostMapping
    public ResponseEntity<ConcertId> createConcert(
            @RequestBody CreateConcertRequestDto requestDto) {

        return concertService.createConcert(requestDto.title(), requestDto.dateTime(), new VendorId())
                .map(concert -> ResponseEntity.ok(concert.getId()))
                .getOrElseThrow(() -> new RuntimeException("oh..."));
    }
}
