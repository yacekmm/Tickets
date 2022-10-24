package com.bottega.vendor.concert.domain;

import com.bottega.vendor.shared.ddd.AggregateRoot;
import com.bottega.vendor.shared.repo.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
public class Concert implements BaseEntity {

    @EmbeddedId
    private ConcertId id;

    @Embedded
    private Title title;

    @Embedded
    private ConcertDate date;

    @Getter(NONE)
    private String vendorId;


    //applyDiscount
}
