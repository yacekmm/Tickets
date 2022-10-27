package com.bottega.vendor.concert.fixtures.fixtures;

import com.bottega.vendor.concert.domain.Concert;
import com.bottega.vendor.concert.domain.ConcertDate;
import com.bottega.vendor.concert.domain.ConcertId;
import com.bottega.vendor.concert.domain.Title;
import com.bottega.vendor.concert.infra.repo.ConcertRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.bottega.sharedlib.config.TestClockConfig.TEST_TIME_PLUS_30_DAYS;

@Component
@RequiredArgsConstructor
public class ConcertBuilder {

    private final ConcertRepo concertRepo;

    public Concert build(){
        return new Concert(new ConcertId(), Title.from("mock-title"), ConcertDate.from(TEST_TIME_PLUS_30_DAYS.toString()), "mock-vendor-id");
    }

    public Concert inDb() {
        return concertRepo.save(build());
    }
}
