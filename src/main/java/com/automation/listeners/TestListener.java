package com.automation.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.automation.utils.ExtentManager;
import com.automation.utils.ScreenshotUtil;
import com.automation.baseClass.BaseClass;
import com.automation.baseClass.BaseClass;

public class TestListener implements ITestListener {

    ExtentReports extent = ExtentManager.getInstance();

    ExtentTest test;

    //private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result)
    {
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result)
    {
        test.pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result)
    {   
    test.fail(result.getThrowable());

        String path =
            ScreenshotUtil.captureScreenshot(
                    BaseClass.getDriver(),
                    result.getMethod().getMethodName());

    try
    {
        test.addScreenCaptureFromPath(path);
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
    
    }

    @Override
    public void onFinish(ITestContext context)
    {
        extent.flush();
    }
}