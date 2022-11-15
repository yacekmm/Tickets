package com.bottega.vendor.concert.domain;

import com.bottega.sharedlib.ddd.ValueObject;
import com.bottega.sharedlib.vo.error.ErrorResult;
import io.vavr.control.Validation;
import lombok.*;

import javax.persistence.*;
import java.util.List;

import static java.util.List.of;

@ValueObject
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Title {

    private static final int TITLE_MIN_LEN = 10;
    private static final int TITLE_MAX_LEN = 160;

    @Transient
    private static final List<String> BANNED_WORDS = of("rage", "violence", "snickers");

    @Getter
    @Column(name = "title")
    private String value;

    public static Validation<ErrorResult, Title> from(String title) {
        return null;
    }
}
