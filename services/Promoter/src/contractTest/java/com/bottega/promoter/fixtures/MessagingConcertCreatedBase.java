package com.bottega.promoter.fixtures;

import static com.bottega.promoter.concert.fixtures.clients.ConcertHttpClient.ConcertRequest.builder;
import static java.time.LocalDate.parse;
import static java.time.ZoneOffset.UTC;

public class MessagingConcertCreatedBase extends CdcFrameworkTestBase {


    protected void createConcert(String title, String dateString, String promoterId) {
        //TODO: implement code creating concert, to trigger CONCERT_CREATED event
    }
}
