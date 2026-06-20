package com.automation.pageClasses;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.baseClass.BaseClass;

public class DashBoardPage extends BaseClass{

    @FindBy(xpath = "//ul[contains(@class,'main-menu')]/li")
    List<WebElement> menuList;

    public DashBoardPage(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void listOfMenu()
    {
        for(WebElement list : menuList)
        {

            System.out.println(list.getText().trim());

            String my_Info = "My Info";

            if(my_Info.equalsIgnoreCase(list.getText().trim()))
            {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.elementToBeClickable(list));
                list.click();
                break;
            }
        }
    }
}
