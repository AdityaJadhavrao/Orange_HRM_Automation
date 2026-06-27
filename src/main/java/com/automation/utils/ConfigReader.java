package com.automation.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    private Properties prop;

    // Only one object
    private static ConfigReader instance = new ConfigReader();

    // Private constructor
    private ConfigReader() {

        prop = new Properties();

        try {

            String path = System.getProperty("user.dir") + "/src/main/resources/config.properties";

            FileInputStream fis = new FileInputStream(path);

            prop.load(fis);

        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

    // Return same object every time (Singleton class)
    public static ConfigReader getInstance() {
        return instance;
    }

    public String getProperty(String key) {
        return prop.getProperty(key);
    }

}