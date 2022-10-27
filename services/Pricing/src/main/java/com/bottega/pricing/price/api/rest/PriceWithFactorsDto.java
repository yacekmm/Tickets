package com.bottega.pricing.price.api.rest;

import com.bottega.pricing.price.domain.ItemPrice;
import com.bottega.pricing.price.domain.PriceFactor;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
@Getter
public class PriceWithFactorsDto {

    private final String priceId;
    private final String itemId;
    private final int price;
    private final List<PriceFactorDto> factors;


    public static PriceWithFactorsDto from(ItemPrice price) {
        return new PriceWithFactorsDto(
                price.getId().asString(),
                price.getItemId(),
                price.getPrice().toInt(),
                price.getPriceFactors().stream().map(PriceFactorDto::from).toList()
        );
    }

    private record PriceFactorDto(
            String type,
            int value) {

        public static PriceFactorDto from(PriceFactor factor) {
            return new PriceFactorDto(
                    factor.getFactorPolicy().getType().name(),
                    factor.getFactorPolicy().getValue()
            );
        }
    }
}
