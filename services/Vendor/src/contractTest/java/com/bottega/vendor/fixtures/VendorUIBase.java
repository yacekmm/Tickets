package com.bottega.vendor.fixtures;

import org.junit.jupiter.api.BeforeEach;

import static com.bottega.sharedlib.config.TestClockConfig.*;

public class VendorUIBase extends CdcFrameworkTestBase {

    @BeforeEach
    public void setup(){
        builders.dontLook().withTitle("Rihanna in Rome").withDate(TEST_TIME_PLUS_30_DAYS).withVendorId("vendor-id").inDb();
        builders.dontLook().withTitle("Rock concert 2").withDate(TEST_TIME_PLUS_60_DAYS).withVendorId("vendor-id").inDb();
    }

}
