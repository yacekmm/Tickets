package com.bottega.vendor.concertRead.fixtures;

import com.bottega.sharedlib.infra.repo.InMemoryRepo;
import com.bottega.vendor.concert.domain.*;
import com.bottega.vendor.concertRead.ConcertFinderRepo;

import java.util.List;

import static java.util.Comparator.comparing;

public class InMemoryConcertFinderRepo
        extends InMemoryRepo<Concert, ConcertId>
        implements ConcertFinderRepo {

    @Override
    public List<Concert> findByVendorIdOrderByDateAsc(String vendorId) {
        return database.values().stream()
                .filter(concert -> concert.vendorId().getValue().equals(vendorId))
                .sorted(comparing(concert -> concert.getDate().getUtcDate()))
                .toList();
    }
}
