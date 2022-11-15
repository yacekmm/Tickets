package com.bottega.vendor.concert.domain;

import com.bottega.sharedlib.config.TestClockConfig;
import com.bottega.vendor.concert.fixtures.ConcertLogicTestBase;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.HashSet;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class InitConcert_UnitTest extends ConcertLogicTestBase {

    public static Stream<Arguments> provideStringsForCategories() {
        return Stream.of(
                Arguments.of("no category", "other"),
                Arguments.of("Rock concert", "rock"),
                Arguments.of("Scorpions in Warsaw", "rock"),
                Arguments.of("Scorpions on Mystic Festival", "rock"),
                Arguments.of("Rihanna the best of", "superstar")
        );
    }

    @ParameterizedTest
    @MethodSource("provideStringsForCategories")
    public void initConcert_assignsCategory(String title, String expectedCategory) {
        //given
        Concert newConcert = new Concert(new ConcertId(), Title.from(title).get(), ConcertDate.from(TestClockConfig.TEST_TIME_PLUS_30_DAYS.toString(), sharedFixtures.clock).get(), "vendor-id", new HashSet<>(), null);
        CategoryService categoryService = mock(CategoryService.class);

        //when
        newConcert.initNewConcert(categoryService);

        //then
        assertThat(newConcert.getCategory().getValue()).isEqualTo(expectedCategory);
    }
}