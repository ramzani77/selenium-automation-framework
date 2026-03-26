package com.sameer.framework.driver.factory;

import com.sameer.framework.driver.DriverManager;
import com.sameer.framework.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DriverFactory {

    private static final Logger log = LogManager.getLogger(DriverFactory.class);

    public static void initDriver(String browser) {

        if (browser == null) {
            throw new RuntimeException("Browser is not provided");
        }

        log.info("Initializing browser");
        log.info("Browser from TestNG: " + browser);

        WebDriver driver;

        switch (browser.toLowerCase()) {

            case "chrome":
                log.info("Launching Chrome browser");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;

            case "firefox":
                log.info("Launching firefox browser");
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            case "edge":
                log.info("Launching edge browser");
                driver = new EdgeDriver(); // no WebDriverManager
                break;

            default:
                throw new RuntimeException("Invalid browser: " + browser);
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        DriverManager.setDriver(driver);
        log.info("Browser launched successfully");
    }
}