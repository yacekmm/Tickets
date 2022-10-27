package com.bottega.pricing.price.api;

import com.bottega.pricing.price.domain.ItemPrice;
import com.bottega.pricing.price.domain.PriceFactor;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
@Getter
public class PricesWithFactorsDto {

    private final List<PriceWithFactorsDto> prices;

    public static PricesWithFactorsDto from(List<ItemPrice> itemPrices) {
        return new PricesWithFactorsDto(
                itemPrices.stream()
                        .map(PriceWithFactorsDto::from)
                        .toList()
        );
    }

    private record PriceWithFactorsDto(
            String priceId,
            String itemId,
            int price,
            List<PriceFactorDto> factors

    ) {


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
                        factor.getFactorXXX().getType().name(),
                        factor.getFactorXXX().getValue()
                );
            }
        }
    }
}
