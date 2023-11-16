package com.bottega.pricing.price.domain;


import com.bottega.sharedlib.ddd.DomainEntity;
import com.bottega.sharedlib.repo.BaseEntity;
import com.bottega.sharedlib.vo.Money;
import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

@DomainEntity
@Entity
@Table(name = "price_factors")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PriceFactor implements BaseEntity {

    @EmbeddedId
    private FactorId id;

    @ManyToOne
    private ItemPrice price;

    @Column(name = "policy", columnDefinition = "jsonb")
    @Type(JsonType.class)
    FactorPolicy factorPolicy;

    //TODO/JM: unit test
    public Money applyToPrice(Money price) {
        return factorPolicy.applyToPrice(price);
    }
}
