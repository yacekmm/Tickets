package com.bottega.promoter.concert.fixtures;

import java.time.*;
import java.util.HashSet;

import com.bottega.promoter.concert.domain.*;
import com.bottega.promoter.concert.infra.repo.ConcertRepo;
import com.bottega.promoter.concertRead.ConcertFinderRepo;
import org.springframework.stereotype.Component;
import static com.bottega.sharedlib.config.TestClockConfig.TEST_TIME_PLUS_30_DAYS;

@Component
public class Builder {

    private final ConcertRepo concertRepo;
    private final ConcertFinderRepo concertFinderRepo;
    private final Clock clock;
    private final Concert.ConcertBuilder builder;

    public Builder(ConcertRepo concertRepo, ConcertFinderRepo concertFinderRepo, Clock clock) {
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

    public Builder withTitle(String title) {
        builder.title(Title.from(title).get());
        return this;
    }

    public Builder withDate(Instant date) {
        builder.date(ConcertDate.from(date.toString(), clock).get());
        return this;
    }

    public Builder withPromoterId(String promoterId) {
        builder.promoterId(promoterId);
        return this;
    }

    public Builder withId(String id) {
        builder.id(new ConcertId(id));
        return this;
    }
}
