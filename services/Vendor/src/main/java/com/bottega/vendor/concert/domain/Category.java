package com.bottega.vendor.concert.domain;

import com.bottega.sharedlib.ddd.DomainEntity;
import com.bottega.sharedlib.repo.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.*;

import static lombok.AccessLevel.PRIVATE;

@DomainEntity
@Entity
@Table(name = "categories")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
@ToString
public class Category implements BaseEntity {

    @EmbeddedId
    @EqualsAndHashCode.Include
    @Getter
    private CategoryId id;

    @Column(name = "category_name")
    @Getter
    private String value;

    @OneToMany(mappedBy = "category")
    private List<Concert> concerts;

    public static Category from(String value) {
        return new Category(new CategoryId(), value, new ArrayList<>());
    }
}
