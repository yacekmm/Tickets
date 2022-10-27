package com.bottega.vendor.fixtures;

import java.time.Instant;

public class MessagingConcertCreatedBase extends CdcFrameworkTestBase {


    protected void createConcert(String title, Instant dateTime, String vendorId) {
        concertFixtures.concertClient.createConcert(title, dateTime.toString(), vendorId);
    }
}
