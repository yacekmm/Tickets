package com.bottega.vendor.concert.fixtures;

import com.bottega.vendor.concert.domain.*;
import com.bottega.vendor.concert.infra.repo.ConcertRepo;
import org.springframework.stereotype.Component;

import java.time.*;
import java.util.HashSet;

import static com.bottega.sharedlib.config.TestClockConfig.TEST_TIME_PLUS_30_DAYS;

@Component
public class ConcertBuilder {

    private final ConcertRepo concertRepo;
    private final Clock clock;
    private final Concert.ConcertBuilder builder;

    public ConcertBuilder(ConcertRepo concertRepo, Clock clock) {
        this.concertRepo = concertRepo;
        this.clock = clock;
        this.builder = Concert.builder()
                .id(new ConcertId())
                .tags(new HashSet<>())
                .category(null);

        this.withTitle("mock-title")
                .withDate(TEST_TIME_PLUS_30_DAYS)
                .vendorId("mock-vendor-id");
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

    public ConcertBuilder withDate(Instant date) {
        builder.date(ConcertDate.from(date.toString(), clock).get());
        return this;
    }

    public ConcertBuilder vendorId(String vendorId) {
        builder.vendorId(vendorId);
        return this;
    }
}
