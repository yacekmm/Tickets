package com.bottega.pricing.price.domain;


import com.bottega.sharedlib.ddd.DomainEntity;
import com.bottega.sharedlib.repo.BaseEntity;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;

@DomainEntity
@Entity
@Table(name = "price_factors")
@TypeDefs({@TypeDef(name = "jsonb", typeClass = JsonStringType.class)})
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PriceFactor implements BaseEntity {

    @EmbeddedId
    private FactorId id;

    @ManyToOne
    private ItemPrice price;

    @Column(name = "XXX")
    @Type(type = "jsonb")
    private FactorXXX factorXXX;

}
