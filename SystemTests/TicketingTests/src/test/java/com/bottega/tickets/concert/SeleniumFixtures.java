package com.bottega.tickets.concert;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@AllArgsConstructor
public class SeleniumFixtures {

    public WebDriver driver;

    public static SeleniumFixtures init(String url) {
        WebDriverManager.chromedriver().setup();
        SeleniumFixtures fixtures = new SeleniumFixtures(new ChromeDriver());
        fixtures.driver.get(url);
        return fixtures;
    }

    void tearDown() {
        driver.close();
    }
}
