package com.bottega.vendor.concert.domain;

import com.bottega.vendor.contract.VendorId;
import com.bottega.vendor.infra.repo.BaseEntity;
import com.bottega.vendor.shared.ddd.AggregateRoot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@AggregateRoot
@Entity
@Table(name = "concerts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Concert implements BaseEntity {

    @EmbeddedId
    private ConcertId id;

    @Embedded
    private Title title;

    @Embedded
    private ConcertDate date;

    @Embedded
    private VendorId vendorId;


    //applyDiscount
}
