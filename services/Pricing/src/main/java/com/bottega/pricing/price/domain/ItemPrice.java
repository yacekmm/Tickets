package com.bottega.pricing.price.domain;

import com.bottega.sharedlib.ddd.AggregateRoot;
import com.bottega.sharedlib.repo.BaseEntity;
import com.bottega.sharedlib.repo.MoneyDbEntity;
import lombok.*;

import javax.persistence.*;

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
    private MoneyDbEntity price;

    private String itemId;

}
