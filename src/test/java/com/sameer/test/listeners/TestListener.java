package com.sameer.test.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class TestListener implements ITestListener {

    private static final Logger log = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Test Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("Test Failed: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        try {
            Path source = Path.of("src/test/resources/environment.properties");
            Path destination = Path.of("target/allure-results/environment.properties");

            Files.createDirectories(destination.getParent());
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);

            log.info("Environment file added to Allure report");

        } catch (IOException e) {
            log.error("Failed to copy environment.properties", e);
        }
    }
}