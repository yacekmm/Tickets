package com.bottega.vendor.concert.domain;

import com.bottega.sharedlib.ddd.DomainEntity;
import lombok.*;

import javax.persistence.*;

import static lombok.AccessLevel.PRIVATE;

@DomainEntity
@Entity
@Table(name = "tags")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
@ToString
public class Category {

    public static final Category EMPTY = new Category(new CategoryId(""), "");
    public static final Category OTHER = new Category(new CategoryId("00000000-0000-0000-0000-000000000000"), "other");

    @EmbeddedId
    @EqualsAndHashCode.Include
    private CategoryId id;

    @Column(name = "value")
    @Getter
    private String value;

    public static Category from(String value) {
        return new Category(new CategoryId(), value);
    }
}
