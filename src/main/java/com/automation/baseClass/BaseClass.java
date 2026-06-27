package com.automation.baseClass;

import java.io.ObjectInputFilter.Config;
import java.time.Duration;
import java.util.Map;
import java.util.HashMap;


import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.automation.factory.DriverFactory;
import com.automation.utils.CleanupUtil;
import com.automation.utils.ConfigReader;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseClass {

    public static WebDriver driver;

    public static Logger logger = LogManager.getLogger(BaseClass.class);

    @BeforeSuite

    public void setUp()
    {
        ConfigReader config = ConfigReader.getInstance();

        CleanupUtil.clearArtifacts();

        String browser = System.getProperty("browser");

        System.out.println("Browser from Maven : " + browser);

        if(browser==null || browser.trim().isEmpty())
        {
            browser = config.getProperty("browser");
        }

        driver = DriverFactory.getDriver(browser);

        driver.get(config.getProperty("url"));

        logger.info("Application Launched Successfully");

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    
    @AfterSuite
    public void tearDown()
    {
        driver.quit();
    }
}
