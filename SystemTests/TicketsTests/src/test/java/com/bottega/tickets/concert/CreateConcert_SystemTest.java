package com.bottega.tickets.concert;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;

import java.time.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

public class CreateConcert_SystemTest {

    private SeleniumFixtures seleniumFixtures;

    @BeforeEach
    void setUp() {
        seleniumFixtures = SeleniumFixtures.init("http://localhost:4200");
    }

    @AfterEach
    void tearDown() {
        seleniumFixtures.tearDown();
    }

    @Test
    void createsConcert() {
        String concertTitle = "GUI test concert " + LocalDateTime.now();

        seleniumFixtures.driver.findElement(By.id("menu-concerts-add")).click();
        seleniumFixtures.driver.findElement(By.id("input-concert-title")).sendKeys(concertTitle);
        seleniumFixtures.driver.findElement(By.className("mat-calendar-next-button")).click();
        seleniumFixtures.driver.findElement(By.className("mat-calendar-next-button")).click();
        seleniumFixtures.driver.findElements(By.className("mat-calendar-body-cell-content")).stream()
                .filter(webElement -> webElement.getText().contains("10"))
                .findFirst()
                .orElseThrow()
                .click();
        seleniumFixtures.driver.findElement(By.id("create-concert-submit")).click();
        await().untilAsserted(() -> assertThat(seleniumFixtures.driver.findElement((By.className("mat-simple-snackbar"))).isDisplayed()).isTrue());

        seleniumFixtures.driver.findElement(By.id("menu-concerts-list")).click();
        String date = seleniumFixtures.driver.findElement(By.id("concerts-list"))
                .findElements(By.className("mat-row")).stream()
                .filter(webElement -> webElement.findElement(By.className("concert-title")).getText().equals(concertTitle))
                .findFirst()
                .orElseThrow()
                .findElement(By.className("concert-date"))
                .getText();

        LocalDate parsedDate = LocalDate.parse(date);
        LocalDate expectedDate = LocalDate.now().plusMonths(2).withDayOfMonth(10);
        assertThat(parsedDate).isEqualTo(expectedDate);
    }

}
