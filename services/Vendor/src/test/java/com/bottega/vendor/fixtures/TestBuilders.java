package com.bottega.vendor.fixtures;

import com.bottega.vendor.agreements.fixtures.VendorAgreementBuilder;
import com.bottega.vendor.concert.fixtures.ConcertBuilder;
import com.bottega.vendor.concert.infra.repo.ConcertRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Clock;

@Component
@AllArgsConstructor
public class TestBuilders {

    private ConcertRepo concertRepo;
    private Clock clock;

    public ConcertBuilder aConcert() {
        return new ConcertBuilder(concertRepo, clock);
    }

    public VendorAgreementBuilder aVendorAgreement() {
        return new VendorAgreementBuilder();
    }
}
