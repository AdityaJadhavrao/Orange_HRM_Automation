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
        
        String expectedTitle = "OrangeHRM";
        String actualTitile = getDriver().getTitle();
        Assert.assertEquals(expectedTitle, actualTitile);
    }

    @Test(dependsOnMethods = "verifyLoginPageTitle")
    public void verifyLoginFunctionality() throws InterruptedException
    {
      ConfigReader config = ConfigReader.getInstance();
      login = new LoginPage(getDriver());
      login.credentials(config.getProperty("username"),config.getProperty("password"));
      Thread.sleep(2000);
    }

    @Test(dependsOnMethods = "verifyLoginFunctionality")
    public void dashBoardFunctionality() throws InterruptedException
    {
      dash = new DashBoardPage(getDriver());
      dash.listOfMenu();
      Thread.sleep(10000);
      Assert.assertTrue(false);
    }

    @Test(dependsOnMethods = "dashBoardFunctionality")
    public void recruitmentFunctionality() throws InterruptedException
    {
      rec = new RecruitmentPage(getDriver());
      rec.RecruitmentTab();
      Thread.sleep(10000);
      rec.candidateList();
      Thread.sleep(10000);
      rec.downloadButtonClick();
      Thread.sleep(10000);
    }
}