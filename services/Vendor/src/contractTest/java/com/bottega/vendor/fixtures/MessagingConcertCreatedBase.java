package com.bottega.vendor.fixtures;

public class MessagingConcertCreatedBase extends CdcFrameworkTestBase {


    protected void createConcert(String title, String dateString, String vendorId) {
        concertFixtures.concertHttpClient.createConcert(title, dateString, vendorId);
    }
}
