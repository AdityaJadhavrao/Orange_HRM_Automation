package com.automation.factory;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public final class DriverFactory {

    private DriverFactory() {
        // Prevent object creation
    }

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void initializeDriver(String browser) {

        if (browser == null || browser.isBlank()) {
            throw new RuntimeException("Browser name cannot be null or empty.");
        }

        switch (browser.toLowerCase()) {

            case "chrome":
                driver.set(createChromeDriver());
                break;

            case "firefox":
                driver.set(createFirefoxDriver());
                break;

            case "edge":
                driver.set(createEdgeDriver());
                break;

            default:
                throw new RuntimeException("Invalid Browser : " + browser);
        }
    }

    public static void quitDriver() {

        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

    private static WebDriver createChromeDriver() {

        WebDriverManager.chromedriver().setup();

        return new ChromeDriver(getChromeOptions());
    }

    private static WebDriver createFirefoxDriver() {

        WebDriverManager.firefoxdriver().setup();

        return new FirefoxDriver();
    }

    private static WebDriver createEdgeDriver() {

        return new EdgeDriver();
    }

    private static ChromeOptions getChromeOptions() {

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-popup-blocking");

        Map<String, Object> prefs = new HashMap<>();

        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        options.setExperimentalOption("prefs", prefs);

        return options;
    }
}