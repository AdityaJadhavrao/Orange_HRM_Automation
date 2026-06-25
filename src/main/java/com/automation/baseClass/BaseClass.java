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
        WebDriverManager.chromedriver().setup();

        ChromeOptions option = new ChromeOptions();
        option.addArguments("--disable-notifications");
        option.addArguments("--disable-infobars");
        option.addArguments("--disable-popup-blocking");

        Map<String,Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        option.setExperimentalOption("prefs", prefs);


        driver = new ChromeDriver(option);

        ConfigReader config = new ConfigReader();

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
