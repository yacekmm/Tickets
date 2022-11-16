package com.bottega.tickets.concert.fixtures;

import com.bottega.tickets.concert.SeleniumFixtures;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;

@AllArgsConstructor
public class ConcertOperations {

    private SeleniumFixtures seleniumFixtures;

    public static ConcertOperations init(SeleniumFixtures seleniumFixtures) {
        return new ConcertOperations(seleniumFixtures);
    }

    public ConcertOperations openCreateConcertView() {
        seleniumFixtures.driver.findElement(By.id("menu-concerts-add")).click();
        return this;
    }
}
