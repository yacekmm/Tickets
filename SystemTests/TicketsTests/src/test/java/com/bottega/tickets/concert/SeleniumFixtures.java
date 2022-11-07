package com.bottega.tickets.concert;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumFixtures {

    WebDriver driver;

    public SeleniumFixtures() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

}
