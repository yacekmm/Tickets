package com.bottega.promoter.fixtures;

import com.bottega.promoter.concertRead.*;
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

    public static ConcertReadFixtures init() {
        ConcertReadFixtures fixtures = new ConcertReadFixtures();
        fixtures.concertFinderRepo = new InMemoryConcertFinderRepo();
        fixtures.concertReadService = new ConcertReadService(
                fixtures.concertFinderRepo);

        return fixtures;
    }


    public void tearDown() {
        concertFinderRepo.deleteAll();
    }
}
