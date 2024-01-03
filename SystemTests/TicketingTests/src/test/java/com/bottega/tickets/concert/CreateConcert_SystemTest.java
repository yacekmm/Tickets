package com.bottega.tickets.concert;

import java.time.LocalDateTime;

import com.bottega.tickets.concert.fixtures.ConcertOperations;
import org.junit.jupiter.api.*;

public class CreateConcert_SystemTest {

    private SeleniumFixtures seleniumFixtures;
    private ConcertOperations concertOperations;

    @BeforeEach
    void setUp() {
        seleniumFixtures = SeleniumFixtures.init("http://localhost:4200");
        concertOperations = ConcertOperations.init(seleniumFixtures);
    }

    @AfterEach
    void tearDown() {
        seleniumFixtures.tearDown();
    }

    @Test
    void createsConcert() {

        //TODO using concertOperations test happy path of core scenario: creating concert and listing it

    }

}
