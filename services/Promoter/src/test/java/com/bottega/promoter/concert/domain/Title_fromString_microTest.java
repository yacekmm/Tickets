package com.bottega.promoter.concert.domain;

import com.bottega.promoter.concert.fixtures.*;
import org.junit.jupiter.api.Test;
import static com.bottega.promoter.concert.domain.Title.from;
import static com.bottega.promoter.concert.fixtures.TitleAssert.assertThatTitle;
import static org.apache.commons.lang3.StringUtils.repeat;

class Title_fromString_microTest extends ConcertLogicTestBase {

    @Test
    public void fromString_OK_onValidString(){
        //expect
        assertThatTitle(from("This is a valid title for a concert")).hasValue("This is a valid title for a concert");
    }

    @Test
    public void fromString_trimsTrailingAndLeadingWhitespaces(){
        //given
        String expectedValue = repeat("a", 10);

        //expect
        assertThatTitle(from("     " + expectedValue + "    ")).hasValue(expectedValue);
    }

    @Test
    public void fromString_honorsLengthLimits(){
        //expect
        assertThatTitle(from(repeat("a", 10))).isValid();
        assertThatTitle(from(repeat("a", 160))).isValid();

        assertThatTitle(from(repeat("a", 9))).hasInvalidLengthError();
        assertThatTitle(from(repeat("a", 161))).hasInvalidLengthError();

        assertThatTitle(from(repeat("a", 9) + " ")).hasInvalidLengthError();
        assertThatTitle(from(repeat("a", 160) + " ")).isValid();

        assertThatTitle(from(null)).hasInvalidLengthError();
    }

    @Test
    public void fromString_reportsFirstBannedWord_onBannedWordInTitle(){
        //expect
        assertThatTitle(from("rage against the machine")).hasBannedWordsError("rage");
        assertThatTitle(from("VIOLENCE")).hasBannedWordsError("violence");
        assertThatTitle(from("   Snickers   ")).hasBannedWordsError("snickers");

        assertThatTitle(from("rage against the machine")).hasBannedWordsError("rage");
        assertThatTitle(from("machine against the rage")).hasBannedWordsError("rage");
        assertThatTitle(from("machine rage machine")).hasBannedWordsError("rage");
    }
}