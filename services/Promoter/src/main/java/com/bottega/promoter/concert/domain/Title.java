package com.bottega.promoter.concert.domain;

import java.util.*;

import com.bottega.promoter.concert.api.app.ConcertErrorCode;
import com.bottega.sharedlib.ddd.ValueObject;
import com.bottega.sharedlib.vo.error.ErrorResult;
import io.vavr.control.Validation;
import jakarta.persistence.*;
import lombok.*;
import static com.bottega.sharedlib.vo.error.ErrorResult.badRequest;
import static java.util.List.of;
import static org.apache.commons.lang3.StringUtils.*;

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
        //TODO implementation
        return null;

    }
}
