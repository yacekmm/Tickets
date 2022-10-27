package com.bottega.vendor.concert.domain;

import com.bottega.sharedlib.ddd.AggregateRoot;
import com.bottega.sharedlib.repo.BaseEntity;
import com.bottega.vendor.agreements.VendorId;
import lombok.*;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import static lombok.AccessLevel.NONE;

@AggregateRoot
@Entity
@Table(name = "concerts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Concert implements BaseEntity {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private ConcertId id;

    @Embedded
    private Title title;

    @Embedded
    private ConcertDate date;

    @Getter(NONE)
    private String vendorId;

    public VendorId vendorId() {
        return new VendorId(vendorId);
    }


    //applyDiscount
}
