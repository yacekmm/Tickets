package com.bottega.vendor.fixtures;

import com.bottega.vendor.agreements.fixtures.VendorAgreementBuilder;
import com.bottega.vendor.concert.fixtures.ConcertBuilder;
import com.bottega.vendor.concert.infra.repo.ConcertRepo;
import com.bottega.vendor.concertRead.ConcertFinderRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Clock;

@Component
@AllArgsConstructor
public class TestBuilders {

    private ConcertRepo concertRepo;
    private ConcertFinderRepo concertFinderRepo;
    private Clock clock;

    public ConcertBuilder aConcert() {
        return new ConcertBuilder(concertRepo, concertFinderRepo, clock);
    }

    public VendorAgreementBuilder aVendorAgreement() {
        return new VendorAgreementBuilder();
    }
}
