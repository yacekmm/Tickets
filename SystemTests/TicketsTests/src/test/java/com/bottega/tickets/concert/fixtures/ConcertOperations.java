package com.bottega.tickets.concert.fixtures;

import com.bottega.tickets.concert.SeleniumFixtures;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

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

    public ConcertOperations createConcert(String concertTitle, int monthsAhead, int dayOfMonth) {
        seleniumFixtures.driver.findElement(By.id("input-concert-title")).sendKeys(concertTitle);

        for (int i=0; i< monthsAhead; i++){
            seleniumFixtures.driver.findElement(By.className("mat-calendar-next-button")).click();
        }

        seleniumFixtures.driver.findElements(By.className("mat-calendar-body-cell-content")).stream()
                .filter(webElement -> webElement.getText().contains(String.valueOf(dayOfMonth)))
                .findFirst()
                .orElseThrow()
                .click();

        seleniumFixtures.driver.findElement(By.id("create-concert-submit")).click();

        return this;
    }

    public ConcertOperations assertCreationSuccess() {
        await().untilAsserted(() -> assertThat(seleniumFixtures.driver.findElement((By.className("mat-simple-snackbar"))).isDisplayed()).isTrue());
        return this;
    }

    public ConcertOperations openConcertListView() {
        seleniumFixtures.driver.findElement(By.id("menu-concerts-list")).click();
        return this;
    }

    public ConcertOperations assertNewConcertIsListed(String concertTitle, int monthsAhead, int dayOfMonth) {
        String date = seleniumFixtures.driver.findElement(By.id("concerts-list"))
                .findElements(By.className("mat-row")).stream()
                .filter(webElement -> webElement.findElement(By.className("concert-title")).getText().equals(concertTitle))
                .findFirst()
                .orElseThrow()
                .findElement(By.className("concert-date"))
                .getText();

        LocalDate parsedDate = LocalDate.parse(date);
        LocalDate expectedDate = LocalDate.now().plusMonths(monthsAhead).withDayOfMonth(dayOfMonth);
        assertThat(parsedDate).isEqualTo(expectedDate);
        return this;
    }
}
