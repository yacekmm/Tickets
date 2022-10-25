package com.bottega.vendor.concert.tests.fixtures;

import com.bottega.vendor.concert.infra.repo.ConcertRepo;
import com.bottega.vendor.concert.tests.clients.ConcertApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FrameworkConcertFixtures {

    public final ConcertRepo concertRepo;

    public final ConcertApiClient concertClient;

}
