package com.bottega.promoter.concert.domain;

import com.bottega.promoter.concert.fixtures.ConcertLogicTestBase;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Concert_initConcert_unitTest extends ConcertLogicTestBase {

    @Test
    void initConcert_addsTags() {
        //TODO tests
        //given

        //when

        //then

    }

    private static Stream<Arguments> provideStringsForCategories() {
        return Stream.of(
                Arguments.of("no category", "other"),
                Arguments.of("Rock concert", "rock"),
                Arguments.of("Scorpions in Warsaw!", "rock"),
                Arguments.of("Scorpions on Mystic Festival", "rock"),
                Arguments.of("Rihanna the best of", "superstar")
        );
    }

    @ParameterizedTest
    @MethodSource("provideStringsForCategories")
    void initConcert_assignsCategory(String title, String expectedCategory) {
        //given
        Concert newConcert = builders.aConcert().withTitle(title).build();

        //when
        newConcert.initNewConcert(concertFixtures.categoryService);

        //then
        assertThat(newConcert.getCategory().getValue()).isEqualTo(expectedCategory);
    }


}