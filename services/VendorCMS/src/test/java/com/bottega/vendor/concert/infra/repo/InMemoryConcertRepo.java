package com.bottega.vendor.concert.infra.repo;

import com.bottega.sharedlib.infra.repo.InMemoryRepo;
import com.bottega.vendor.concert.domain.Concert;
import com.bottega.vendor.concert.domain.ConcertId;

public class InMemoryConcertRepo
        extends InMemoryRepo<Concert, ConcertId>
        implements ConcertRepo {
}
