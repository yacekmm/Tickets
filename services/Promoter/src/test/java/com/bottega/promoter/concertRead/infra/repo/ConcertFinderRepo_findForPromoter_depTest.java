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
        fakeRepo = new InMemoryConcertFinderRepo();
        realRepo = concertReadFixtures.concertFinderRepo;
    }

    @Test
    public void findForPromoter_isValid() {
        //given
        Concert concert_1 = builders.aConcert().withDate(TEST_TIME_PLUS_30_DAYS).withPromoterId("promoter").inFinderDb();
        Concert concert_2 = builders.aConcert().withDate(TEST_TIME_PLUS_60_DAYS).withPromoterId("promoter").inFinderDb();
        Concert other = builders.aConcert().withPromoterId("other").inDb();

        fakeRepo.saveAll(of(concert_1, concert_2, other));
        realRepo.saveAll(of(concert_1, concert_2, other));

        //when
        List<Concert> fakeRepoResult = fakeRepo.findByPromoterIdOrderByDateAsc("promoter");
        List<Concert> realRepoResult = realRepo.findByPromoterIdOrderByDateAsc("promoter");

        //then
        assertThat(fakeRepoResult).containsExactlyElementsOf(realRepoResult);
    }

}
