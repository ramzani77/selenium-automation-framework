package com.sameer.test.listeners;

import com.sameer.framework.utils.ScreenshotUtils;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.qameta.allure.Allure;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TestListener implements ITestListener {

    private static final Logger log = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestFailure(ITestResult result) {

        String testName = result.getName();

        log.error("Test Failed: " + testName);

        String screenshotPath = ScreenshotUtils.captureScreenshot(testName);

        log.error("Screenshot saved at: " + screenshotPath);

        // Attach screenshot to Allure
        try {
            byte[] fileContent = java.nio.file.Files.readAllBytes(
                    java.nio.file.Paths.get(screenshotPath)
            );

            Allure.addAttachment(
                    "Failure Screenshot",
                    "image/png",
                    new java.io.ByteArrayInputStream(fileContent),
                    ".png"
            );

            log.error("Screenshot attached to Allure report");

        } catch (Exception e) {
            log.error("Failed to attach screenshot", e);
        }
    }
}