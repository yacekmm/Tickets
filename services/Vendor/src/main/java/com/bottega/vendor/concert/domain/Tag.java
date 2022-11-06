package com.bottega.vendor.concert.domain;

import com.bottega.sharedlib.ddd.DomainEntity;
import com.bottega.sharedlib.repo.BaseEntity;
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
public class Tag implements BaseEntity {

    @EmbeddedId
    @Getter
    private TagId id;

    @EqualsAndHashCode.Include
    @Column(name = "tag_name")
    @Getter
    private String value;

    public static Tag from(String value) {
        return new Tag(new TagId(), value);
    }
}
