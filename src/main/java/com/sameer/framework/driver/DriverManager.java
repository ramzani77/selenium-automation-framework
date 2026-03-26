package com.sameer.framework.driver;
import org.openqa.selenium.WebDriver;
import java.lang.ThreadLocal;

//Class to store WebDriver Object (DriverFactory class create the driver)
//This class will manage the driver instances for entire framework

public class DriverManager {

    //It creates separate driver instance per thread and stores the driver per thread.
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    //It stores the driver in ThreadLocal.
    public static void setDriver(WebDriver webdriver){
        driver.set(webdriver);
    }

    //This allows any class to access the driver.
    public static WebDriver getDriver(){
        return driver.get();
    }

    public static void unload(){
        driver.remove();
    }

}
