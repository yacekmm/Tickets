package com.bottega.pricing.tests;

import com.bottega.pricing.factor.tests.FrameworkFactorFixtures;
import com.bottega.pricing.price.tests.FrameworkPriceFixtures;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@SpringBootTest(webEnvironment = DEFINED_PORT)
@ActiveProfiles({"test"})
public class FrameworkTestBase {

    @Autowired
    protected FrameworkFactorFixtures factorFixtures;

    @Autowired
    protected FrameworkPriceFixtures priceFixtures;


    @AfterEach
    void tearDown() {
        factorFixtures.tearDown();
    }

}
