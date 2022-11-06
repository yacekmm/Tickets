package com.bottega.vendor.fixtures;

import org.junit.jupiter.api.BeforeEach;

import static com.bottega.sharedlib.config.TestClockConfig.*;

public class VendorUIBase extends CdcFrameworkTestBase {

    @BeforeEach
    public void setup(){
        concertFixtures.concertBuilder.withTitle("Rihanna in Rome").withDate(TEST_TIME_PLUS_30_DAYS).vendorId("vendor-id").inDb();
        concertFixtures.concertBuilder.withTitle("Rock concert 2").withDate(TEST_TIME_PLUS_60_DAYS).vendorId("vendor-id").inDb();
    }

}
