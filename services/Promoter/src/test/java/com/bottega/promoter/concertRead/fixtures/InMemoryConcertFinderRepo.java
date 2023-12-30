package com.bottega.promoter.concertRead.fixtures;

import java.util.List;

import com.bottega.promoter.concert.domain.*;
import com.bottega.promoter.concertRead.ConcertFinderRepo;
import com.bottega.sharedlib.infra.repo.InMemoryRepo;
import static java.util.Comparator.comparing;

public class InMemoryConcertFinderRepo
        extends InMemoryRepo<Concert, ConcertId>
        implements ConcertFinderRepo {

    @Override
    public List<Concert> findByPromoterIdOrderByDateAsc(String promoterId) {
        return database.values().stream()
                .filter(concert -> concert.promoterId().getValue().equals(promoterId))
                .sorted(comparing(concert -> concert.getDate().getUtcDate()))
                .toList();
    }
}
