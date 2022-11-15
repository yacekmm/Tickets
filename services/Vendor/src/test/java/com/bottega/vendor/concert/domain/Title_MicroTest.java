package com.bottega.vendor.concert.domain;

import com.bottega.vendor.concert.fixtures.ConcertLogicTestBase;
import org.junit.jupiter.api.Test;

import static com.bottega.vendor.concert.domain.Title.from;
import static org.assertj.core.api.Assertions.assertThat;

class Title_MicroTest extends ConcertLogicTestBase {

    @Test
    public void fromString_OK_onValidString(){
        //expect
        assertThat(from("This is a valid title").get().getValue()).isEqualTo("This is a valid title");
    }
}