package com.bottega.promoter.fixtures;

import com.bottega.promoter.concertRead.ConcertFinderRepo;
import com.bottega.promoter.concertRead.ConcertPriceRepo;
import com.bottega.promoter.concertRead.ConcertReadService;
import com.bottega.promoter.concertRead.fixtures.InMemoryConcertFinderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConcertReadFixtures {

    //SUTs
    @Autowired
    public ConcertReadService concertReadService;

    //repos
    @Autowired
    public ConcertFinderRepo concertFinderRepo;
    @Autowired
    public ConcertPriceRepo concertPriceRepo;

    public static ConcertReadFixtures init() {
        ConcertReadFixtures fixtures = new ConcertReadFixtures();
        fixtures.concertFinderRepo = new InMemoryConcertFinderRepo();
        fixtures.concertReadService = new ConcertReadService(
                fixtures.concertFinderRepo,
                fixtures.concertPriceRepo
                );

        return fixtures;
    }


    public void tearDown() {
        concertFinderRepo.deleteAll();
    }
}
