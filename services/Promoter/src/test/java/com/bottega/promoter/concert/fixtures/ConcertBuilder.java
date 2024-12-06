package com.bottega.promoter.concert.fixtures;

import com.bottega.promoter.concert.domain.Concert;
import com.bottega.promoter.concert.domain.ConcertDate;
import com.bottega.promoter.concert.domain.ConcertId;
import com.bottega.promoter.concert.domain.Title;
import com.bottega.promoter.concert.infra.repo.ConcertRepo;
import com.bottega.promoter.concertRead.ConcertFinderRepo;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Instant;
import java.util.HashSet;

import static com.bottega.sharedlib.config.TestClockConfig.TEST_TIME_PLUS_30_DAYS;

@Component
public class ConcertBuilder {

    private final ConcertRepo concertRepo;
    private final ConcertFinderRepo concertFinderRepo;
    private final Clock clock;
    private final Concert.ConcertBuilder builder;

    public ConcertBuilder(ConcertRepo concertRepo, ConcertFinderRepo concertFinderRepo, Clock clock) {
        this.concertRepo = concertRepo;
        this.concertFinderRepo = concertFinderRepo;
        this.clock = clock;
        this.builder = Concert.builder()
                .id(new ConcertId())
                .tags(new HashSet<>())
                .category(null);

        this.withTitle("mock title of a concert")
                .withDate(TEST_TIME_PLUS_30_DAYS)
                .withPromoterId("mock-promoter-id");
    }

    public Concert build() {
        return builder.build();
    }

    public Concert inDb() {
        return concertRepo.save(build());
    }

    public Concert inFinderDb() {
        return concertFinderRepo.save(build());
    }

    public ConcertBuilder withTitle(String title) {
        builder.title(Title.from(title).get());
        return this;
    }

    public ConcertBuilder withDate(Instant date) {
        builder.date(ConcertDate.from(date.toString(), clock).get());
        return this;
    }

    public ConcertBuilder withPromoterId(String promoterId) {
        builder.promoterId(promoterId);
        return this;
    }

    public ConcertBuilder withId(String id) {
        builder.id(new ConcertId(id));
        return this;
    }
}
