package com.bottega.promoter.concert;

import com.bottega.sharedlib.vo.Money;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode
@AllArgsConstructor
@ToString
@Getter
public class Price {

    private String id;
    private String itemId;
    private Money price;
    private List<PriceFactor> factors;

    public Price applyFactor(PriceFactor factor) {
        this.price = price.subtract(price.percentage(factor.value()));
        this.factors.add(factor);
        return this;
    }

}
