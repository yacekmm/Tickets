package com.bottega.promoter.concert.domain;

import com.bottega.promoter.concert.fixtures.*;
import org.junit.jupiter.api.Test;
import static com.bottega.promoter.concert.domain.Title.from;
import static org.apache.commons.lang3.StringUtils.repeat;

class Title_fromString_microTest extends ConcertLogicTestBase {

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
        TitleAssert.assertThatTitle(from(repeat("a", 10))).isValid();
        TitleAssert.assertThatTitle(from(repeat("a", 160))).isValid();

        TitleAssert.assertThatTitle(from(repeat("a", 9))).hasInvalidLengthError();
        TitleAssert.assertThatTitle(from(repeat("a", 161))).hasInvalidLengthError();

        TitleAssert.assertThatTitle(from(repeat("a", 9) + " ")).hasInvalidLengthError();
        TitleAssert.assertThatTitle(from(repeat("a", 160) + " ")).isValid();

        TitleAssert.assertThatTitle(from(null)).hasInvalidLengthError();
    }

    @Test
    public void fromString_reportsFirstBannedWord_onBannedWordInTitle(){
        //expect
        TitleAssert.assertThatTitle(from("rage against the machine")).hasBannedWordsError("rage");
        TitleAssert.assertThatTitle(from("VIOLENCE")).hasBannedWordsError("violence");
        TitleAssert.assertThatTitle(from("   Snickers   ")).hasBannedWordsError("snickers");

        TitleAssert.assertThatTitle(from("rage against the machine")).hasBannedWordsError("rage");
        TitleAssert.assertThatTitle(from("machine against the rage")).hasBannedWordsError("rage");
        TitleAssert.assertThatTitle(from("machine rage machine")).hasBannedWordsError("rage");
    }
}