package com.sameer.framework.utils;

import com.sameer.framework.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private static final int TimeOut = 10;

    public static void waitForElementToBeClickable(By locator) {
        new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TimeOut))
        .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForElementToBeVisible(By locator) {
        new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TimeOut))
        .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

}

