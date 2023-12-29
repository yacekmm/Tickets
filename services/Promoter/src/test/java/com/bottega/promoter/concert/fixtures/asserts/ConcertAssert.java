package com.bottega.promoter.concert.fixtures.asserts;

import java.time.Instant;
import java.util.*;

import com.bottega.promoter.agreements.PromoterId;
import com.bottega.promoter.concert.domain.*;
import com.bottega.promoter.concert.infra.repo.ConcertRepo;
import com.bottega.sharedlib.fixtures.RepoEntries;
import lombok.RequiredArgsConstructor;
import static java.time.ZoneOffset.UTC;
import static org.assertj.core.api.Assertions.*;

@RequiredArgsConstructor
public class ConcertAssert {

    private final Concert concert;

    public static ConcertAssert assertThatConcert(Concert concert) {
        return new ConcertAssert(concert);
    }

    public ConcertAssert hasTitle(String expectedTitle) {
        assertThat(concert.getTitle().getValue()).isEqualTo(expectedTitle);
        return this;
    }

    public ConcertAssert hasDate(Instant expectedDate) {
        assertThat(concert.getDate().getUtcDate()).isEqualTo(expectedDate.atZone(UTC).toLocalDate());
        return this;
    }

    public ConcertAssert hasPromoterId(String expectedPromoterId) {
        assertThat(concert.promoterId().asString()).isEqualTo(expectedPromoterId);
        return this;
    }

    public ConcertAssert hasPromoterId(PromoterId promoterId) {
        return hasPromoterId(promoterId.asString());
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
