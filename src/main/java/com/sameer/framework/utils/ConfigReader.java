package com.sameer.framework.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties prop;

    static {
        try{
            FileInputStream fis = new FileInputStream("config/config.properties");
            prop = new Properties();
            prop.load(fis);
        }catch(IOException e){
            throw new RuntimeException("Failed to load config.properties");
        }
    }
    public static String get(String key) {
        return prop.getProperty(key);
    }
}
