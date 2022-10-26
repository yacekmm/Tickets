package com.bottega.vendor.concert.infra.repo;

import com.bottega.vendor.concert.domain.Concert;
import com.bottega.vendor.concert.domain.ConcertId;
import com.bottega.vendor.tests.infra.repo.InMemoryRepo;

public class InMemoryConcertRepo
        extends InMemoryRepo<Concert, ConcertId>
        implements ConcertRepo {
}
