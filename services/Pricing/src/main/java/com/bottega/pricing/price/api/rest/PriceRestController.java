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

    @PostMapping(path = V1 + "/item/{itemId}/price-factor/percentage")
    @ResponseBody
    @Transactional
    public List<PriceWithFactorsDto> applyPercentageFactor(
            @PathVariable("itemId") String itemId,
            @RequestBody PercentageFactorRequestDto factorRequestDto) {

        return priceService.applyPercentageFactor(itemId, factorRequestDto.percentage())
                .map(itemPrices -> itemPrices.stream().map(PriceWithFactorsDto::from).toList())
                .getOrElseThrow(ErrorResult::toException);
    }

}
