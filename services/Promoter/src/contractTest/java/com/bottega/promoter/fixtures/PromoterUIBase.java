package com.bottega.promoter.fixtures;

import org.junit.jupiter.api.BeforeEach;
import static com.bottega.sharedlib.config.TestClockConfig.*;

public class PromoterUIBase extends CdcFrameworkTestBase {

    @BeforeEach
    public void setup(){
        builders.aConcert().withTitle("Rihanna in Rome").withDate(TEST_TIME_PLUS_30_DAYS).withPromoterId("promoter-id").inDb();
        builders.aConcert().withTitle("Rock concert 2").withDate(TEST_TIME_PLUS_60_DAYS).withPromoterId("promoter-id").inDb();
    }

}
