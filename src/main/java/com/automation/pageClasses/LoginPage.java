package com.automation.pageClasses;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.automation.baseClass.BaseClass;


public class LoginPage extends BaseClass {
    
    @FindBy(xpath = "//input[@name='username']")
    private WebElement username;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement password;

    @FindBy(xpath = "//button[contains(@class,'login-button')]")
    private WebElement loginButton;

    WebDriverWait wait;

    public LoginPage(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }

    public void credentials(String name, String pwd)  // --> (Sting name, String pwd) should be consolidated into a single function
    {
        //System.out.println(Thread.currentThread().getName() + " Entering credentials");

        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(username));
        username.sendKeys(name);
        logger.info("Username entered");

        wait.until(ExpectedConditions.visibilityOf(password));
        password.sendKeys(pwd);
        logger.info("Password entered");

        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
        logger.info("Login button clicked");
    }

}
