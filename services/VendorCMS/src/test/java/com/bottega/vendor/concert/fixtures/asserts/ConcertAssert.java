package com.bottega.vendor.concert.fixtures.asserts;

import com.bottega.sharedlib.tests.RepoEntries;
import com.bottega.vendor.concert.domain.Concert;
import com.bottega.vendor.concert.domain.ConcertId;
import com.bottega.vendor.concert.infra.repo.ConcertRepo;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

@RequiredArgsConstructor(staticName = "assertThatConcert")
public class ConcertAssert {

    private final Concert concert;

    public ConcertAssert hasTitle(String expectedTitle) {
        assertThat(concert.getTitle().getValue()).isEqualTo(expectedTitle);
        return this;
    }

    public ConcertAssert hasDateTime(Instant expectedDateTime) {
        assertThat(concert.getDate().getDateTime()).isEqualTo(expectedDateTime);
        return this;
    }

    public ConcertAssert hasVendorId(String expectedVendorId) {
        assertThat(concert.vendorId().asString()).isEqualTo(expectedVendorId);
        return this;
    }

    public ConcertAssert isPersistedIn(ConcertRepo concertRepo, RepoEntries entries) {
        assertThat(concertRepo.findAll()).hasSize(entries.allEntriesCount());
        assertThat(concertRepo.findById(concert.getId())).hasValue(concert);
        return this;
    }

    public ConcertAssert hasIdAsUUID() {
        assertThatNoException().isThrownBy(() -> UUID.fromString(concert.getId().asString()));
        return this;
    }

    public ConcertId extractId() {
        return concert.getId();
    }
}
