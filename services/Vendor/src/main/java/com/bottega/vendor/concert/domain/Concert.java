package com.bottega.vendor.concert.domain;

import com.bottega.sharedlib.ddd.AggregateRoot;
import com.bottega.sharedlib.repo.BaseEntity;
import com.bottega.vendor.agreements.VendorId;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.*;

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

    private static final Map<String, String[]> defaultCategories = new HashMap<>();

    static {
        defaultCategories.put("rock", new String[]{"Rock", "Scorpions"});
        defaultCategories.put("superstar", new String[]{"Rihanna"});
    }

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
            joinColumns = {@JoinColumn(name = "concert_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")}
    )
    private Set<Tag> tags;
    @ManyToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "concerts")
    private Category category;

    public VendorId vendorId() {
        return new VendorId(vendorId);
    }

    public void initNewConcert() {
        category = defaultCategories.entrySet().stream()
                .filter(entry -> StringUtils.containsAnyIgnoreCase(title.getValue(), entry.getValue()))
                .findFirst()
                .map(entry -> Category.from(entry.getKey()))
                .orElse(Category.from("other"));
    }
}
