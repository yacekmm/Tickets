package com.bottega.pricing.price.domain;


import com.bottega.sharedlib.ddd.DomainEntity;
import com.bottega.sharedlib.repo.BaseEntity;
import lombok.Getter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;

@DomainEntity
@Entity
@Table(name = "price_factors")
@Getter
public class PriceFactor implements BaseEntity {

    @EmbeddedId
    private FactorId id;

    @Column(name = "price_id")
    private String priceId;

    @Column(name = "type", nullable = false)
    @Enumerated(STRING)
    private FactorType type;

    @Column(name = "XXX")
    @Type(type = "jsonb")
    private FactorXXX factorXXX;

}
