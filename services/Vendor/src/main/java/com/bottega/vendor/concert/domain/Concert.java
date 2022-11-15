package com.bottega.vendor.concert.domain;

import com.bottega.sharedlib.ddd.AggregateRoot;
import com.bottega.sharedlib.repo.BaseEntity;
import com.bottega.vendor.agreements.VendorId;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.*;

@AggregateRoot
@Entity
@Table(name = "concerts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Builder(access = PUBLIC)
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

    @ManyToMany(fetch = LAZY, cascade = PERSIST)
    @JoinTable(
            name = "concert_tags",
            joinColumns = { @JoinColumn(name = "concert_id") },
            inverseJoinColumns = { @JoinColumn(name = "tag_id") }
    )
    private Set<Tag> tags;

    @ManyToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "concerts")
    private Category category;


    public VendorId vendorId() {
        return new VendorId(vendorId);
    }

}
