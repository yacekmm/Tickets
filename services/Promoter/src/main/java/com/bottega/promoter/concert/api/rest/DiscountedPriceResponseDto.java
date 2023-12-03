package com.bottega.promoter.concert.api.rest;

import com.bottega.promoter.concert.Price;

public record DiscountedPriceResponseDto(
        int price) {

    public static DiscountedPriceResponseDto fromPrice(Price price) {
        return new DiscountedPriceResponseDto(price.getPrice().toInt());
    }
}
