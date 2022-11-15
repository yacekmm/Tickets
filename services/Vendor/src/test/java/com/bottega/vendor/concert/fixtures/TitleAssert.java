package com.bottega.vendor.concert.fixtures;

import com.bottega.sharedlib.vo.error.ErrorResult;
import com.bottega.vendor.concert.domain.Title;
import io.vavr.control.Validation;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;

import static org.assertj.vavr.api.VavrAssertions.assertThat;

@RequiredArgsConstructor(staticName = "assertThatTitle")
public class TitleAssert {

    private final Validation<ErrorResult, Title> title;


    public TitleAssert hasValue(String expectedValue) {

        assertThat(title).isValid();
        Assertions.assertThat(title.get().getValue()).isEqualTo(expectedValue);
        return this;
    }

}
