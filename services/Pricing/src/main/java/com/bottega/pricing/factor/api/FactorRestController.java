package com.bottega.pricing.factor.api;

import com.bottega.pricing.factor.application.api.FactorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static com.bottega.sharedlib.config.ApiVersions.V1;

@RestController
@AllArgsConstructor
public class FactorRestController {

    private final FactorService factorService;

    @PostMapping(path = V1 + "/item/{itemId}/price-factor/percentage")
    @ResponseBody
    public ResponseEntity<Void> applyPercentageFactor(
            @PathVariable("itemId") String itemId) {

        return ResponseEntity.noContent().build();
//        return factorService.createConcert(requestDto.title(), requestDto.dateTime(), requestDto.vendorId())
//                .map(concert -> fromAggregateId(concert.getId()))
//                .getOrElseThrow(ErrorResult::toException);
    }

}
