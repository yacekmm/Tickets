package com.bottega.vendor.concert.fixtures;

import com.bottega.vendor.concert.domain.*;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.util.HashSet;

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
                .vendorId("mock-vendor-id")
                .tags(new HashSet<>())
                .category(null);
    }

}
