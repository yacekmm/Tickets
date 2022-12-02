package com.bottega.vendor.fixtures;

import static com.bottega.vendor.concert.fixtures.clients.ConcertHttpClient.ConcertRequest.builder;
import static java.time.LocalDate.parse;
import static java.time.ZoneOffset.UTC;

public class MessagingConcertCreatedBase extends CdcFrameworkTestBase {


    protected void createConcert(String title, String dateString, String vendorId) {
        concertFixtures.concertHttpClient.createConcert(builder()
                .title(title)
                .date(parse(dateString).atStartOfDay().toInstant(UTC))
                .vendorId(vendorId)
                .build());
    }
}
