package com.bottega.promoter.concert.fixtures;

import com.bottega.promoter.concert.domain.Title;
import com.bottega.sharedlib.vo.error.*;
import io.vavr.control.Validation;
import lombok.RequiredArgsConstructor;
import static com.bottega.promoter.concert.api.app.ConcertErrorCode.invalid_title;
import static com.bottega.sharedlib.vo.error.ErrorType.BAD_REQUEST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.vavr.api.VavrAssertions.assertThat;

@RequiredArgsConstructor
public class TitleAssert {

    private final Validation<ErrorResult, Title> title;

    public static TitleAssert assertThatTitle(Validation<ErrorResult, Title> title) {
        return new TitleAssert(title);
    }


    public TitleAssert hasValue(String expectedValue) {

        assertThat(title).isValid();
        assertThat(title.get().getValue()).isEqualTo(expectedValue);
        return this;
    }

    //TODO implement
}
