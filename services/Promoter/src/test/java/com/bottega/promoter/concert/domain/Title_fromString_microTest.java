package com.bottega.promoter.concert.domain;

import com.bottega.promoter.concert.fixtures.*;
import org.junit.jupiter.api.Test;
import static com.bottega.promoter.concert.domain.Title.from;
import static com.bottega.promoter.concert.fixtures.TitleAssert.assertThatTitle;
import static org.apache.commons.lang3.StringUtils.repeat;
import static org.assertj.core.api.Assertions.assertThat;

class Title_fromString_microTest extends ConcertLogicTestBase {

    //TODO tests

    @Test
    public void fromString_OK_onValidString(){
        //expect
        assertThat(from("This is a valid title for a concert").get().getValue()).isEqualTo("This is a valid title for a concert");
    }

}