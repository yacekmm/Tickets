package com.bottega.vendor.concert.domain;

import com.bottega.sharedlib.ddd.AggregateRoot;
import com.bottega.sharedlib.repo.BaseEntity;
import com.bottega.vendor.agreements.VendorId;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
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

    @ManyToMany(mappedBy = "tags", fetch = LAZY, cascade = ALL)
    private Set<Tag> tags;


    public VendorId vendorId() {
        return new VendorId(vendorId);
    }


    public void initNewConcert(TagService tagService) {
        tags = tagService.tag(title);
    }

}
