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
        this.driver = driver;
    }

    public void enterUsername(String name, String pwd)  // --> (Sting name, String pwd) should be consolidated into a single function
    {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(username));
        username.sendKeys(name);
        wait.until(ExpectedConditions.visibilityOf(password));
        password.sendKeys(pwd);

        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }

    // public void enterPassword(String pwd)
    // {
    //     wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    //     wait.until(ExpectedConditions.visibilityOf(password));
    //     password.sendKeys(pwd);
    // }

    // public void loginButtonClick()
    // {
    //     wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    //     wait.until(ExpectedConditions.elementToBeClickable(loginButton));
    //     loginButton.click();
    // }

}
