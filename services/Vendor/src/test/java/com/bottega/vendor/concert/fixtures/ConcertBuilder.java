package com.bottega.vendor.concert.fixtures;

import com.bottega.vendor.concert.domain.*;
import com.bottega.vendor.concert.infra.repo.ConcertRepo;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.util.HashSet;

import static com.bottega.sharedlib.config.TestClockConfig.TEST_TIME_PLUS_30_DAYS;

@Component
public class ConcertBuilder {

    private final ConcertRepo concertRepo;
    private final Concert.ConcertBuilder builder;

    public ConcertBuilder(ConcertRepo concertRepo, Clock clock) {
        this.concertRepo = concertRepo;
        this.builder = Concert.builder()
                .id(new ConcertId())
                .title(Title.from("mock-title"))
                .date(ConcertDate.from(TEST_TIME_PLUS_30_DAYS.toString(), clock).get())
                .vendorId("mock-vendor-id")
                .tags(new HashSet<>())
                .category(null);
    }

    public Concert build() {
        return builder.build();
    }

    public Concert inDb() {
        return concertRepo.save(build());
    }

    public ConcertBuilder withTitle(String title) {
        builder.title(Title.from(title));
        return this;
    }
}
