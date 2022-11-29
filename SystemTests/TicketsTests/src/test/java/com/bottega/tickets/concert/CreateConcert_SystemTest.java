package com.bottega.tickets.concert;

import com.bottega.tickets.concert.fixtures.ConcertOperations;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;

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
        String concertTitle = "GUI test concert " + LocalDateTime.now();


        concertOperations
                .openCreateConcertView()
                .createConcert(concertTitle, 2, 10)
                .assertCreationSuccess()
                .openConcertListView()
                .assertNewConcertIsListed(concertTitle, 2, 10);
    }

}
