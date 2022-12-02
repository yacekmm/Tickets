package com.bottega.vendor.concertRead.infra.repo;

import java.util.List;

import com.bottega.vendor.concert.domain.Concert;
import com.bottega.vendor.concertRead.ConcertFinderRepo;
import com.bottega.vendor.concertRead.fixtures.InMemoryConcertFinderRepo;
import com.bottega.vendor.fixtures.FrameworkTestBase;
import org.junit.jupiter.api.*;
import static com.bottega.sharedlib.config.TestClockConfig.*;
import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;

public class ConcertFinderRepo_findForVendor_depTest extends FrameworkTestBase {

    private InMemoryConcertFinderRepo fakeRepo;
    private ConcertFinderRepo realRepo;

    @BeforeEach
    void setUp() {
        fakeRepo = new InMemoryConcertFinderRepo();
        realRepo = concertReadFixtures.concertFinderRepo;
    }

    @Test
    public void findForVendor_isValid() {
        //given
        Concert concert_1 = builders.aConcert().withDate(TEST_TIME_PLUS_30_DAYS).withVendorId("vendor").inFinderDb();
        Concert concert_2 = builders.aConcert().withDate(TEST_TIME_PLUS_60_DAYS).withVendorId("vendor").inFinderDb();
        Concert other = builders.aConcert().withVendorId("other").inDb();

        fakeRepo.saveAll(of(concert_1, concert_2, other));
        realRepo.saveAll(of(concert_1, concert_2, other));

        //when
        List<Concert> fakeRepoResult = fakeRepo.findByVendorIdOrderByDateAsc("vendor");
        List<Concert> realRepoResult = realRepo.findByVendorIdOrderByDateAsc("vendor");

        //then
        assertThat(fakeRepoResult).containsExactlyElementsOf(realRepoResult);
    }

}
