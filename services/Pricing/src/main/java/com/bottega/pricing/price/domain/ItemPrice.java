package com.bottega.pricing.price.domain;

import com.bottega.sharedlib.ddd.AggregateRoot;
import com.bottega.sharedlib.repo.BaseEntity;
import com.bottega.sharedlib.repo.MoneyDbEntity;
import com.bottega.sharedlib.vo.Money;
import lombok.*;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.NONE;

@AggregateRoot
@Entity
@Table(name = "prices")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Getter
public class ItemPrice implements BaseEntity {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private PriceId id;

    @Embedded
    @Column(name = "current_price")
    @Getter(NONE)
    private MoneyDbEntity price;

    @Column(name = "item_id")
    private String itemId;

    @OneToMany(fetch = LAZY, cascade = ALL)
    private List<PriceFactor> priceFactors;

    public ItemPrice applyFactor(PriceFactor e) {
        this.price = MoneyDbEntity.from(getPrice().percentage(100 - e.getFactorXXX().getValue()));
        this.priceFactors.add(e);
        return this;
    }

    public Money getPrice(){
        return price.toMoney();
    }
}
