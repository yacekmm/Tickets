package com.bottega.vendor.concertRead.fixtures;

import java.util.*;

import com.bottega.sharedlib.infra.repo.InMemoryRepo;
import com.bottega.vendor.concert.domain.*;
import com.bottega.vendor.concertRead.ConcertFinderRepo;

public class InMemoryConcertFinderRepo
        extends InMemoryRepo<Concert, ConcertId>
        implements ConcertFinderRepo {

    @Override
    public List<Concert> findByVendorIdOrderByDateAsc(String vendorId) {
        return new ArrayList<>();
    }
}
