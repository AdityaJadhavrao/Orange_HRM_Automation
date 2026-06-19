package com.automation.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    Properties prop;

    public ConfigReader()
    {
        prop = new Properties();
        try
        {
            FileInputStream fis = new FileInputStream("/home/prithvirajjadhavrao/Documents/selenium-framework/src/main/resources/config.properties");
            prop.load(fis);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public String getProperty(String key)
    {
        return prop.getProperty(key);
    }
    
}
