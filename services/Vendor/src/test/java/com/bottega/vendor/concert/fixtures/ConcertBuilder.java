package com.bottega.vendor.concert.fixtures;

import com.bottega.vendor.concert.domain.*;
import com.bottega.vendor.concert.infra.repo.ConcertRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.util.HashSet;

import static com.bottega.sharedlib.config.TestClockConfig.TEST_TIME_PLUS_30_DAYS;
import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor(access = PRIVATE)
public class ConcertBuilder {

    private final ConcertRepo concertRepo;
    private final Clock clock;
    private final Concert.ConcertBuilder builder;

    public static ConcertBuilder init(ConcertRepo concertRepo, Clock clock) {
        Concert.ConcertBuilder builder = Concert.builder()
                .id(new ConcertId())
                .title(Title.from("mock-title"))
                .date(ConcertDate.from(TEST_TIME_PLUS_30_DAYS.toString(), clock).get())
                .vendorId("mock-vendor-id")
                .tags(new HashSet<>())
                .category(Category.EMPTY);
        return new ConcertBuilder(concertRepo, clock, builder);
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
