package com.bottega.vendor.concert.fixtures.fixtures;

import com.bottega.vendor.concert.fixtures.clients.ConcertApiClient;
import com.bottega.vendor.concert.infra.repo.ConcertRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FrameworkConcertFixtures {

    public final ConcertRepo concertRepo;

    public final ConcertApiClient concertClient;
    public final ConcertBuilder concertBuilder;

    public void tearDown() {
        concertRepo.deleteAll();
    }
}
