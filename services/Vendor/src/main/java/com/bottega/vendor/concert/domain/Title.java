package com.bottega.vendor.concert.domain;

import com.bottega.sharedlib.ddd.ValueObject;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@ValueObject
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Title {

    @Getter
    @Column(name = "title")
    private String value;

    public static Title from(String title) {
        return new Title(title);
    }
}
