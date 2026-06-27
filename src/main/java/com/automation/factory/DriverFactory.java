package com.automation.factory;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    public static WebDriver getDriver(String browser) {

        if(browser == null || browser.trim().isEmpty()) 
        {
            throw new RuntimeException("Browser name cannot be null or empty.");
        }

        switch (browser.toLowerCase()) {

            case "chrome":

                WebDriverManager.chromedriver().setup();

                ChromeOptions options = new ChromeOptions();

                options.addArguments("--disable-notifications");
                options.addArguments("--disable-infobars");
                options.addArguments("--disable-popup-blocking");

                Map<String, Object> prefs = new HashMap<>();

                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);

                options.setExperimentalOption("prefs", prefs);

                return new ChromeDriver(options);

            case "firefox":

                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();

            case "edge":

                //WebDriverManager.edgedriver().setup();
                System.out.println("Inside Edge Case");
                return new EdgeDriver();

            default:

                throw new RuntimeException("Invalid Browser : " + browser);
        }
    }
}