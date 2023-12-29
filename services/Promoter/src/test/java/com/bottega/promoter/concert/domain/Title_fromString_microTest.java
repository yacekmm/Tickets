package com.bottega.promoter.concert.domain;

import com.bottega.promoter.concert.fixtures.*;
import org.assertj.vavr.api.VavrAssertions;
import org.junit.jupiter.api.Test;
import static com.bottega.promoter.concert.api.app.ConcertErrorCode.invalid_title;
import static com.bottega.promoter.concert.domain.Title.from;
import static com.bottega.sharedlib.vo.error.ErrorResult.badRequest;
import static org.apache.commons.lang3.StringUtils.repeat;
import static org.assertj.core.api.Assertions.assertThat;

class Title_fromString_microTest extends ConcertLogicTestBase {

    @Test
    public void fromString_OK_onValidString(){
        //expect
        assertThat(from("This is a valid title for a concert").get().getValue()).isEqualTo("This is a valid title for a concert");
    }

    @Test
    public void fromString_trimsTrailingAndLeadingWhitespaces(){
        //given
        String expectedValue = repeat("a", 10);

        //expect
        assertThat(from("    " + expectedValue + "     ").get().getValue()).isEqualTo(expectedValue);
    }

    @Test
    public void fromString_honorsLengthLimits(){
        //expect
        VavrAssertions.assertThat(from(repeat("a", 10))).isValid();
        VavrAssertions.assertThat(from(repeat("a", 160))).isValid();

        assertInvalidLengthError(repeat("a", 9));
        assertInvalidLengthError(repeat("a", 161));

        assertInvalidLengthError(repeat("a", 9) + " ");
        VavrAssertions.assertThat(from(repeat("a", 160) + " ")).isValid();

        assertInvalidLengthError(null);
    }


    private void assertInvalidLengthError(String input) {
        VavrAssertions.assertThat(from(input))
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
        VavrAssertions.assertThat(from(input))
                .isInvalid()
                .containsInvalid(badRequest(invalid_title, "Title must not contain banned word: " +  bannedWord));
    }
}