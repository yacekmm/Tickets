package com.bottega.vendor.concert.fixtures.asserts;

import com.bottega.sharedlib.fixtures.RepoEntries;
import com.bottega.vendor.agreements.VendorId;
import com.bottega.vendor.concert.domain.*;
import com.bottega.vendor.concert.infra.repo.ConcertRepo;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.*;

import static java.time.ZoneOffset.UTC;
import static org.assertj.core.api.Assertions.*;

@RequiredArgsConstructor(staticName = "assertThatConcert")
public class ConcertAssert {

    private final Concert concert;

    public ConcertAssert hasTitle(String expectedTitle) {
        assertThat(concert.getTitle().getValue()).isEqualTo(expectedTitle);
        return this;
    }

    public ConcertAssert hasDate(Instant expectedDate) {
        assertThat(concert.getDate().getUtcDate()).isEqualTo(expectedDate.atZone(UTC).toLocalDate());
        return this;
    }

    public ConcertAssert hasVendorId(String expectedVendorId) {
        assertThat(concert.vendorId().asString()).isEqualTo(expectedVendorId);
        return this;
    }

    public ConcertAssert hasVendorId(VendorId vendorId) {
        return hasVendorId(vendorId.asString());
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

    public ConcertAssert hasTags(Set<Tag> expectedTags) {
        assertThat(concert.getTags()).containsExactlyElementsOf(expectedTags);
        return this;
    }
}
