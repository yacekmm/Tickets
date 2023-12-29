package com.bottega.promoter.concert.domain;

import com.bottega.promoter.concert.fixtures.ConcertLogicTestBase;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;

class Concert_initConcert_unitTest extends ConcertLogicTestBase {

    private static Stream<Arguments> provideStringsForTags() {
        return Stream.of(
                Arguments.of("no tags apply", Set.of())
                //TODO more cases
        );
    }

    @ParameterizedTest
    @MethodSource("provideStringsForTags")
    void initConcert_addsTags(String title, Set<String> expectedTags) {
        //given
        Concert newConcert = builders.aConcert().withTitle(title).build();

        //when
        newConcert.initNewConcert(concertFixtures.tagService, concertFixtures.categoryService);

        //then
        assertThat(newConcert.getTags().stream().map(Tag::getValue).collect(toSet())).containsExactlyInAnyOrderElementsOf(expectedTags);
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
        newConcert.initNewConcert(concertFixtures.tagService, concertFixtures.categoryService);

        //then
        assertThat(newConcert.getCategory().getValue()).isEqualTo(expectedCategory);
    }


}