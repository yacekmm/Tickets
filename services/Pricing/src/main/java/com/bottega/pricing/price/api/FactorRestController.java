package com.bottega.pricing.price.api;

import com.bottega.pricing.price.domain.ItemPrice;
import com.bottega.sharedlib.vo.error.ErrorResult;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

import static com.bottega.sharedlib.config.ApiVersions.V1;

@RestController
@AllArgsConstructor
public class FactorRestController {

    private final PriceService priceService;

    @PostMapping(path = V1 + "/item/{itemId}/price-factor/percentage")
    @ResponseBody
    @Transactional
    public List<ItemPrice> applyPercentageFactor(
            @PathVariable("itemId") String itemId) {

        return priceService.applyPercentageFactor(itemId, 10)
                .getOrElseThrow(ErrorResult::toException);
    }

}
