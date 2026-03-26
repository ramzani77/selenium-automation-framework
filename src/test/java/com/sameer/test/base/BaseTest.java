package com.sameer.test.base;

import com.sameer.framework.driver.DriverManager;
import com.sameer.framework.driver.factory.DriverFactory;
import com.sameer.framework.utils.ConfigReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Listeners;
import com.sameer.test.listeners.TestListener;
import org.testng.annotations.Parameters;

@Listeners(TestListener.class)

public class BaseTest {
    private static final Logger log = LogManager.getLogger(BaseTest.class);

    @BeforeMethod
    @Parameters("browser")
    public void setup(String browser) {

        log.info("Thread ID: " + Thread.currentThread().getId());
        log.info("===== Test Execution Started =====");

        DriverFactory.initDriver(browser);

        String url = ConfigReader.get("url");

        if (url == null) {
            throw new RuntimeException("URL is not defined in config.properties");
        }

        log.info("Opening URL: " + url);

        DriverManager.getDriver().get(url);
    }

    @AfterMethod
    public void teardown() {

        log.info("Closing browser");

        if (DriverManager.getDriver() != null) {
            DriverManager.getDriver().quit();
            DriverManager.unload();
        }

        log.info("===== Test Execution Finished =====");
    }
}