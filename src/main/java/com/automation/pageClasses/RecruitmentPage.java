package com.automation.pageClasses;

import java.time.Duration;
import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.automation.baseClass.BaseClass;

public class RecruitmentPage extends BaseClass {

    @FindBy(xpath = "//ul[contains(@class,'main-menu')]/li")
    List<WebElement> menuList;

    @FindBy(xpath = "//div[@role='rowgroup'][2]//div[text()='John  Doe']")
    List<WebElement> candidateList;

    @FindBy(xpath = "//div[text()='John  Doe']/../..//i[@class='oxd-icon bi-download']")
    List<WebElement> downloadIcon;

    public RecruitmentPage(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void RecruitmentTab()
    {
        for(WebElement list : menuList)
        {
            String my_Info = "Recruitment";

            if(my_Info.equalsIgnoreCase(list.getText().trim()))
            {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.elementToBeClickable(list));
                list.click();
                break;
            }
        }
        logger.info("Clicked on menuList");
    }

    public void candidateList()
    {
        int count = candidateList.size();

        //System.out.println("Total number of candidates and their names  : " + count);
        logger.info("Total number of candidates and their names : "+count);
    }

    public void downloadButtonClick()
    {

        //System.out.println("Total number of download icons  : " + downloadIcon.size());
        logger.info("Total number of download icons  : " + downloadIcon.size());

        for(WebElement button : downloadIcon)
        {
            if(button.isDisplayed() && button.isEnabled())
            {
                ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", button);

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.elementToBeClickable(button));

                button.click();
            }
        }
    }

    
}
