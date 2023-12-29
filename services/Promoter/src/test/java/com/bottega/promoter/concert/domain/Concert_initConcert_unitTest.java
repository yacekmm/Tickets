package com.bottega.promoter.concert.domain;

import java.util.*;
import java.util.stream.Stream;

import com.bottega.promoter.concert.fixtures.ConcertLogicTestBase;
import com.bottega.sharedlib.config.TestClockConfig;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.mockito.*;
import static com.bottega.sharedlib.config.TestClockConfig.TEST_TIME_PLUS_30_DAYS;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

class Concert_initConcert_unitTest extends ConcertLogicTestBase {

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
        Concert newConcert = new Concert(new ConcertId(), Title.from(title).get(), ConcertDate.from(TEST_TIME_PLUS_30_DAYS.toString(), sharedFixtures.clock).get(), "vendor-id", new HashSet<>(), null);
        CategoryService categoryService = mock(CategoryService.class);
        given(categoryService.categorize(Title.from(title).get())).willReturn(Category.from(expectedCategory));

        //when
        newConcert.initNewConcert(categoryService);

        //then
        then(categoryService).should().categorize(Title.from(title).get());
        assertThat(newConcert.getCategory().getValue()).isEqualTo(expectedCategory);
    }


}