package com.automation.utils;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver, String testName)

    {
        File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        String destination ="Screenshots/" + testName + ".png";

        File destinationPath = new File(destination);

        try
        {
            FileUtils.copyFile(source,destinationPath);
        }

        catch(IOException e)
        {
            e.printStackTrace();
        }

        return destination;
    }
}