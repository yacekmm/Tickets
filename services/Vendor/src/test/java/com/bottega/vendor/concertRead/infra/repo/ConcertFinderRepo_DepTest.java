package com.bottega.vendor.concertRead.infra.repo;

import com.bottega.vendor.concertRead.ConcertFinderRepo;
import com.bottega.vendor.concertRead.fixtures.InMemoryConcertFinderRepo;
import com.bottega.vendor.fixtures.FrameworkTestBase;
import org.junit.jupiter.api.*;

public class ConcertFinderRepo_DepTest extends FrameworkTestBase {

    private InMemoryConcertFinderRepo fakeRepo;
    private ConcertFinderRepo realRepo;

    @BeforeEach
    void setUp() {
        fakeRepo = new InMemoryConcertFinderRepo();
        realRepo = concertReadFixtures.concertFinderRepo;
    }

    @Test
    public void inMemoryConcertFinderRepo_isValid() {
        //given

        //when

        //then

    }

}
