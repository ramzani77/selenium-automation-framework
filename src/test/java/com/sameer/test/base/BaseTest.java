package com.sameer.test.base;

import com.sameer.framework.driver.DriverManager;
import com.sameer.framework.driver.factory.DriverFactory;
import com.sameer.framework.utils.ConfigReader;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Listeners;
import com.sameer.test.listeners.TestListener;
import org.testng.annotations.Parameters;
import org.testng.annotations.Optional;

@Listeners(TestListener.class)
public class BaseTest {

    private static final Logger log = LogManager.getLogger(BaseTest.class);

    @BeforeMethod
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser) {

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
    public void tearDown(ITestResult result) {

        try {
            // 🔥 Capture screenshot ONLY on failure
            if (result.getStatus() == ITestResult.FAILURE) {
                attachScreenshot();
            }

        } catch (Exception e) {
            log.error("Error while capturing screenshot", e);
        }

        // 🔥 Always quit driver safely
        try {
            if (DriverManager.getDriver() != null) {
                log.info("Closing browser");
                DriverManager.getDriver().quit();
            }
        } catch (Exception e) {
            log.error("Error while quitting driver", e);
        }

        log.info("===== Test Execution Finished =====");
    }

    // 🔥 Allure Screenshot Attachment (FINAL WORKING)
    @Attachment(value = "Failure Screenshot", type = "image/png")
    public byte[] attachScreenshot() {

        WebDriver driver = DriverManager.getDriver();

        if (driver == null) {
            return new byte[0];
        }

        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}