package com.bottega.tickets.concert;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class SeleniumFixtures {

    WebDriver driver;

    public SeleniumFixtures(WebDriver driver) {
        WebDriverManager.chromedriver().setup();
        this.driver = driver;
    }

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
