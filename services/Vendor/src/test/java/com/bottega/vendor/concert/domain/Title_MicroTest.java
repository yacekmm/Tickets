package com.bottega.vendor.concert.domain;

import com.bottega.vendor.concert.fixtures.*;
import org.junit.jupiter.api.Test;

import static com.bottega.sharedlib.vo.error.ErrorResult.badRequest;
import static com.bottega.vendor.concert.api.app.ConcertErrorCode.invalid_title;
import static com.bottega.vendor.concert.domain.Title.from;
import static org.apache.commons.lang3.StringUtils.repeat;
import static org.assertj.vavr.api.VavrAssertions.assertThat;

class Title_MicroTest extends ConcertLogicTestBase {

    @Test
    public void fromString_OK_onValidString(){
        //expect
        TitleAssert.assertThatTitle(from("This is a valid title for a concert")).hasValue("This is a valid title for a concert");
    }

    @Test
    public void fromString_trimsTrailingAndLeadingWhitespaces(){
        //given
        String expectedValue = repeat("a", 10);

        //expect
        TitleAssert.assertThatTitle(from("     " + expectedValue + "    ")).hasValue(expectedValue);
    }

    @Test
    public void fromString_honorsLengthLimits(){
        //expect
        assertThat(from(repeat("a", 10))).isValid();
        assertThat(from(repeat("a", 160))).isValid();
        assertThat(from(repeat("a", 160) + " ")).isValid();

        assertInvalidLengthError(repeat("a", 9));
        assertInvalidLengthError(repeat("a", 161));
        assertInvalidLengthError(repeat("a", 9) + " ");
        assertInvalidLengthError(null);
    }

    private void assertInvalidLengthError(String input) {
        assertThat(from(input))
                .isInvalid()
                .containsInvalid(badRequest(invalid_title, "Title length must be between 10 and 160 chars"));
    }

    @Test
    public void fromString_reportsFirstBannedWord_onBannedWordInTitle(){
        //expect
        assertBannedWordsError("rage against the machine", "rage");
        assertBannedWordsError("VIOLENCE", "violence");
        assertBannedWordsError("   Snickers   ", "snickers");

        assertBannedWordsError("rage against the machine", "rage");
        assertBannedWordsError("machine against the rage", "rage");
        assertBannedWordsError("machine rage machine", "rage");
    }

    private void assertBannedWordsError(String input, String bannedWord) {
        assertThat(from(input))
                .isInvalid()
                .containsInvalid(badRequest(invalid_title, "Title must not contain banned word: " +  bannedWord));
    }
}