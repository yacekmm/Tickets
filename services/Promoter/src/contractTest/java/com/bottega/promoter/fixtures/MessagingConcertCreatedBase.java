package com.bottega.promoter.fixtures;

import static com.bottega.promoter.concert.fixtures.clients.ConcertHttpClient.ConcertRequest.builder;
import static java.time.LocalDate.parse;
import static java.time.ZoneOffset.UTC;

public class MessagingConcertCreatedBase extends CdcFrameworkTestBase {


    protected void createConcert(String title, String dateString, String promoterId) {
        concertFixtures.concertHttpClient.createConcert(builder()
                .title(title)
                .date(parse(dateString).atStartOfDay().toInstant(UTC))
                .promoterId(promoterId)
                .build());
    }
}
