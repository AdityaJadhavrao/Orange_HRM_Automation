package com.automation.baseClass;

import java.io.ObjectInputFilter.Config;
import java.time.Duration;
import java.util.Map;
import java.util.HashMap;


import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.automation.factory.DriverFactory;
import com.automation.utils.CleanupUtil;
import com.automation.utils.ConfigReader;
import com.beust.jcommander.Parameter;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseClass {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void setDriver(WebDriver webDriver) 
    {
        driver.set(webDriver);
    }

    public static WebDriver getDriver() 
    {
        return driver.get();
    }

    public static Logger logger = LogManager.getLogger(BaseClass.class);

    @BeforeSuite
    public void beforeSuite() {

        CleanupUtil.clearArtifacts();

    }

    @Parameters("browser")

    @BeforeClass

    public void setUp(@Optional("") String browser)
    {
        ConfigReader config = ConfigReader.getInstance();

        CleanupUtil.clearArtifacts();

        String mavenBrowser = System.getProperty("browser");

        if(mavenBrowser!=null && !mavenBrowser.trim().isEmpty())
        {
            browser = mavenBrowser;

            System.out.println("Browser from Maven : " + browser);
        }
        
        else if (browser != null && !browser.trim().isEmpty()) {

            System.out.println("Browser from TestNG : " + browser);

        }
        else 
        {

            browser = config.getProperty("browser");

            System.out.println("Browser from Config : " + browser);

        }

        setDriver(DriverFactory.getDriver(browser));

        //System.out.println(Thread.currentThread().getName()+ " Driver = " + getDriver());

        getDriver().get(config.getProperty("url"));

        logger.info("Application Launched Successfully");

        getDriver().manage().window().maximize();

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    
    @AfterClass
    public void tearDown()
    {
        if(getDriver()!=null)
        {
            getDriver().quit();
            driver.remove();
        }
    }
}
