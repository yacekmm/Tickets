package com.bottega.vendor.concert.domain;

import com.bottega.vendor.concert.application.api.ConcertService;
import com.bottega.vendor.concert.infra.repo.ConcertRepo;
import com.bottega.vendor.concert.infra.repo.InMemoryConcertRepo;

public class ConcertFixtures {

    //SUTs
    public ConcertService concertService;

    //infrastructure
    public ConcertRepo concertRepo;

    public static ConcertFixtures init() {
        ConcertFixtures concertFixtures = new ConcertFixtures();
        concertFixtures.concertRepo = new InMemoryConcertRepo();

        concertFixtures.concertService = new ConcertService(
                new ConcertFactory(),
                concertFixtures.concertRepo
        );

        return concertFixtures;
    }


}
