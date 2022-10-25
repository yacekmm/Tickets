package com.bottega.vendor.concert;

import com.bottega.sharedlib.vo.Money;

public record Price(
        Money price,
        PriceFactor[] factors
) {

}
