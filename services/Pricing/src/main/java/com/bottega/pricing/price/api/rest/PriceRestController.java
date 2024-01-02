package com.bottega.pricing.price.api.rest;

import java.util.List;

import com.bottega.pricing.price.api.app.PriceService;
import com.bottega.sharedlib.vo.error.ErrorResult;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import static com.bottega.sharedlib.config.ApiVersions.V1;

@RestController
@AllArgsConstructor
public class PriceRestController {

    private final PriceService priceService;

    //TODO specify path
    @ResponseBody
    @Transactional
    public List<PriceWithFactorsDto> applyPercentageFactor(
            @PathVariable("itemId") String itemId,
            @RequestBody PercentageFactorRequestDto factorRequestDto) {

        //TODO implement API to satisfy contract requested by consumer
        return null;
    }

}
