package com.bottega.promoter.concertRead.infra.repo;

import java.util.List;

import com.bottega.promoter.concert.domain.Concert;
import com.bottega.promoter.concertRead.ConcertFinderRepo;
import com.bottega.promoter.concertRead.fixtures.InMemoryConcertFinderRepo;
import com.bottega.promoter.fixtures.FrameworkTestBase;
import org.junit.jupiter.api.*;
import static com.bottega.sharedlib.config.TestClockConfig.*;
import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;

public class ConcertFinderRepo_findForPromoter_depTest extends FrameworkTestBase {

    private InMemoryConcertFinderRepo fakeRepo;
    private ConcertFinderRepo realRepo;

    @BeforeEach
    void setUp() {
        //TODO initialize both repos
    }

    @Test
    public void findForPromoter_isValid() {
        //given
        Concert concert_1 = builders.aConcert().withDate(TEST_TIME_PLUS_30_DAYS).withPromoterId("promoter").inFinderDb();
        Concert concert_2 = builders.aConcert().withDate(TEST_TIME_PLUS_60_DAYS).withPromoterId("promoter").inFinderDb();
        Concert other = builders.aConcert().withPromoterId("other").inDb();

        //TODO save all concerts in both repos

        //when
        //TODO call findForPromoter on both repos

        //then
        //TODO compare results
    }

}
