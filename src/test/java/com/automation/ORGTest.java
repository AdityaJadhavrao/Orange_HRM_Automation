package com.automation;

import org.testng.annotations.Test;

import com.automation.baseClass.BaseClass;
import com.automation.pageClasses.DashBoardPage;
import com.automation.pageClasses.LoginPage;
import com.automation.pageClasses.RecruitmentPage;
import com.automation.utils.ConfigReader;
import org.testng.Assert;

public class ORGTest extends BaseClass {

   LoginPage login;
   DashBoardPage dash;
   RecruitmentPage rec;

    @Test(priority = 1)
    public void verifyLoginPageTitle() {

        System.out.println("Test Executed");
        System.out.println(driver.getTitle());

        String expectedTitle = "OrangeHRM";
        String actualTitile = driver.getTitle();
        Assert.assertEquals(expectedTitle, actualTitile);
    }

    @Test(dependsOnMethods = "verifyLoginPageTitle")
    public void verifyLoginFunctionality() throws InterruptedException
    {
      ConfigReader config = new ConfigReader();
      login = new LoginPage(driver);
      login.credentials(config.getProperty("username"),config.getProperty("password"));
      Thread.sleep(2000);
    }

    @Test(dependsOnMethods = "verifyLoginFunctionality")
    public void dashBoardFunctionality() throws InterruptedException
    {
      dash = new DashBoardPage(driver);
      dash.listOfMenu();
      Thread.sleep(10000);
      Assert.assertTrue(false);
    }

    @Test(dependsOnMethods = "dashBoardFunctionality")
    public void recruitmentFunctionality() throws InterruptedException
    {
      rec = new RecruitmentPage(driver);
      rec.RecruitmentTab();
      Thread.sleep(10000);
      rec.candidateList();
      Thread.sleep(10000);
      rec.downloadButtonClick();
      Thread.sleep(10000);
    }
}