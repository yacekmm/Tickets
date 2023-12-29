package com.bottega.promoter.concert.domain;

import java.util.*;
import java.util.stream.Stream;

import com.bottega.promoter.concert.fixtures.ConcertLogicTestBase;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import static com.bottega.sharedlib.config.TestClockConfig.TEST_TIME_PLUS_30_DAYS;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;

class Concert_initConcert_unitTest extends ConcertLogicTestBase {


    private static Stream<Arguments> provideStringsForTags() {
        return Stream.of(
                Arguments.of("no tags apply", Set.of()),
                Arguments.of("Rock concert", Set.of("rock")),
                Arguments.of("Scorpions in Warsaw!", Set.of("rock")),
                Arguments.of("Scorpions on Mystic Festival", Set.of("rock", "festival")),
                Arguments.of("Rihanna the best of", Set.of("pop"))
        );
    }

    @ParameterizedTest
    @MethodSource("provideStringsForTags")
    void initConcert_addsTags(String title, Set<String> expectedTags) {
        //given
        //TODO use builder
        Concert newConcert = new Concert(new ConcertId(), Title.from(title).get(), ConcertDate.from(TEST_TIME_PLUS_30_DAYS.toString(), sharedFixtures.clock).get(), "vendor-id", new HashSet<>(), null);

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
        //TODO use builder
        Concert newConcert = new Concert(new ConcertId(), Title.from(title).get(), ConcertDate.from(TEST_TIME_PLUS_30_DAYS.toString(), sharedFixtures.clock).get(), "vendor-id", new HashSet<>(), null);

        //when
        newConcert.initNewConcert(concertFixtures.tagService, concertFixtures.categoryService);

        //then
        assertThat(newConcert.getCategory().getValue()).isEqualTo(expectedCategory);
    }


}