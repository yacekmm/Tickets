package com.bottega.vendor.fixtures;

import com.bottega.vendor.concert.fixtures.fixtures.FrameworkConcertFixtures;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@SpringBootTest(webEnvironment = DEFINED_PORT)
@ActiveProfiles({"test"})
public class FrameworkTestBase {

    @Autowired
    protected FrameworkConcertFixtures concertFixtures;

    @AfterEach
    void tearDown() {
        concertFixtures.tearDown();
    }
}
