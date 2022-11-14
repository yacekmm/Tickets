package com.bottega.tickets.concert.fixtures;

import com.bottega.tickets.concert.SeleniumFixtures;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConcertOperations {

    private SeleniumFixtures seleniumFixtures;

    public static ConcertOperations init(SeleniumFixtures seleniumFixtures) {
        return new ConcertOperations(seleniumFixtures);
    }
}
