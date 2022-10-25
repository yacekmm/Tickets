package com.bottega.vendor.concert.domain;

import com.bottega.vendor.concert.application.api.ConcertService;
import com.bottega.vendor.concert.infra.repo.ConcertRepo;
import com.bottega.vendor.concert.infra.repo.InMemoryConcertRepo;

public class ManualConcertFixtures {

    //SUTs
    public ConcertService concertService;

    //infrastructure
    public ConcertRepo concertRepo;

    public static ManualConcertFixtures init() {
        ManualConcertFixtures concertFixtures = new ManualConcertFixtures();
        concertFixtures.concertRepo = new InMemoryConcertRepo();

        concertFixtures.concertService = new ConcertService(
                new ConcertFactory(),
                concertFixtures.concertRepo
        );

        return concertFixtures;
    }


}
