package com.bottega.vendor.concert;

import com.bottega.sharedlib.vo.Money;
import lombok.*;

import java.util.List;

@EqualsAndHashCode
@AllArgsConstructor
@ToString
@Getter
public class Price {

    private Money price;
    private List<PriceFactor> factors;
}
