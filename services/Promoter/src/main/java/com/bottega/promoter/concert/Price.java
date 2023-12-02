package com.bottega.promoter.concert;

import java.util.List;

import com.bottega.sharedlib.vo.Money;
import lombok.*;

@EqualsAndHashCode
@AllArgsConstructor
@ToString
@Getter
public class Price {

    private Money price;
    private List<PriceFactor> factors;
}
