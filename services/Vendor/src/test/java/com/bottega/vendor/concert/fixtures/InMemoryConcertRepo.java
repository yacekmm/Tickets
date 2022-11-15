package com.bottega.vendor.concert.fixtures;

import com.bottega.sharedlib.infra.repo.InMemoryRepo;
import com.bottega.vendor.concert.domain.Concert;
import com.bottega.vendor.concert.domain.ConcertId;
import com.bottega.vendor.concert.infra.repo.ConcertRepo;

public class InMemoryConcertRepo
        extends InMemoryRepo<Concert, ConcertId>
        implements ConcertRepo {
}
