package com.bottega.promoter.concert.fixtures;

import java.time.*;
import java.util.HashSet;

import com.bottega.promoter.concert.domain.*;
import com.bottega.promoter.concert.infra.repo.ConcertRepo;
import com.bottega.promoter.concertRead.ConcertFinderRepo;
import org.springframework.stereotype.Component;
import static com.bottega.sharedlib.config.TestClockConfig.TEST_TIME_PLUS_30_DAYS;

@Component
public class ConcertBuilder {

    private final Clock clock;
    private final Concert.ConcertBuilder builder;

    public ConcertBuilder(Clock clock) {
        this.clock = clock;
        this.builder = Concert.builder()
                .id(new ConcertId())
                .title(Title.from("mock title of a concert").get())
                .date(ConcertDate.from(TEST_TIME_PLUS_30_DAYS.toString(), this.clock).get())
                .promoterId("mock-promoter-id")
                .tags(new HashSet<>())
                .category(null);
    }

    //TODO implement builder
}
