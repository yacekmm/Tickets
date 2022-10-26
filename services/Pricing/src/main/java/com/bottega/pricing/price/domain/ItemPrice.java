package com.bottega.pricing.price.domain;

import com.bottega.sharedlib.ddd.AggregateRoot;
import com.bottega.sharedlib.repo.BaseEntity;
import com.bottega.sharedlib.repo.MoneyDbEntity;
import com.bottega.sharedlib.vo.Money;
import lombok.*;

import javax.persistence.*;

import static lombok.AccessLevel.NONE;

@AggregateRoot
@Entity
@Table(name = "prices")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
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

    public ItemPrice applyPercentageFactor(int percentage) {
        this.price = MoneyDbEntity.from(getPrice().percentage(100 - percentage));
        return this;
    }

    public Money getPrice(){
        return price.toMoney();
    }
}
