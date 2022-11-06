package com.bottega.vendor.concert.domain;

import com.bottega.vendor.concert.fixtures.ConcertLogicTestBase;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;

class Concert_UnitTest extends ConcertLogicTestBase {


    private static Stream<Arguments> provideStringsForTags() {
        return Stream.of(
                Arguments.of("no tags apply", Set.of()),
                Arguments.of("Rock concert", Set.of("rock")),
                Arguments.of("Scorpions in Warsaw!", Set.of("rock")),
                Arguments.of("Scorpions on Mystic Festival", Set.of("rock", "festival")),
                Arguments.of("Rihanna", Set.of("pop"))
        );
    }

    @ParameterizedTest
    @MethodSource("provideStringsForTags")
    void initConcert_addsTags(String title, Set<String> expectedTags) {
        //given
        Concert newConcert = concertFixtures.concertBuilder.withTitle(title).build();

        //when
        newConcert.initNewConcert(concertFixtures.tagService);

        //then
        assertThat(newConcert.getTags().stream().map(Tag::getValue).collect(toSet())).containsExactlyInAnyOrderElementsOf(expectedTags);
    }
}