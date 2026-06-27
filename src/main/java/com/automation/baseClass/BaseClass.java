package com.automation.baseClass;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.automation.factory.DriverFactory;
import com.automation.utils.CleanupUtil;
import com.automation.utils.ConfigReader;

public class BaseClass {

    protected static final Logger logger = LogManager.getLogger(BaseClass.class);

    public static WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    @BeforeSuite
    public void beforeSuite() {

        CleanupUtil.clearArtifacts();
    }

    @BeforeClass
    @Parameters("browser")
    public void setUp(String browser) {

        ConfigReader config = ConfigReader.getInstance();

        logger.info("Launching browser : ", browser);

        DriverFactory.initializeDriver(browser);

        getDriver().get(config.getProperty("url"));

        getDriver().manage().window().maximize();

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        logger.info("Application launched successfully.");
    }

    @AfterClass
    public void tearDown() {

        DriverFactory.quitDriver();
    }
}