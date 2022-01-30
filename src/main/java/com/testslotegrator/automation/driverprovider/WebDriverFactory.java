package com.testslotegrator.automation.driverprovider;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {

    public static WebDriver createWebDriver() {
        String webDriver = System.getProperty("browser", "chrome");
        switch (webDriver) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case "chrome":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            default:
                throw new RuntimeException("Неподдерживаемый  webdriver: " + webDriver);
        }
    }
}

