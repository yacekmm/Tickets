package com.bottega.promoter.concert.fixtures;

import com.bottega.promoter.concert.domain.*;
import com.bottega.promoter.concert.infra.repo.ConcertRepo;
import com.bottega.sharedlib.infra.repo.InMemoryRepo;

public class InMemoryConcertRepo
        extends InMemoryRepo<Concert, ConcertId>
        implements ConcertRepo {
}
