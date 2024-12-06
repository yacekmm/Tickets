package com.bottega.promoter.concert.domain;

import com.bottega.promoter.agreements.PromoterId;
import com.bottega.sharedlib.ddd.AggregateRoot;
import com.bottega.sharedlib.repo.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PUBLIC;

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
    private String promoterId;

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


    public PromoterId promoterId() {
        return new PromoterId(promoterId);
    }


    public void initNewConcert(TagService tagService, CategoryService categoryService) {
        tags = tagService.tag(title);
        category = categoryService.categorize(title);
    }

}
