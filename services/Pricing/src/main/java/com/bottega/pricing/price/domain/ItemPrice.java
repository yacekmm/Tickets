package com.bottega.pricing.price.domain;

import java.util.*;

import com.bottega.sharedlib.ddd.AggregateRoot;
import com.bottega.sharedlib.repo.*;
import com.bottega.sharedlib.vo.Money;
import jakarta.persistence.*;
import lombok.*;
import static com.bottega.sharedlib.repo.MoneyDbEntity.from;
import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.*;
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

    @OneToMany(fetch = EAGER, cascade = ALL)
    private List<PriceFactor> priceFactors;

    public static ItemPrice create(String itemId, Money price) {
        return new ItemPrice(new PriceId(), from(price), itemId, new ArrayList<>());
    }

    public ItemPrice applyFactor(PriceFactor factor) {
        this.price = from(factor.applyToPrice(getPrice()));
        this.priceFactors.add(factor);
        return this;
    }

    public Money getPrice(){
        return price.toMoney();
    }
}
