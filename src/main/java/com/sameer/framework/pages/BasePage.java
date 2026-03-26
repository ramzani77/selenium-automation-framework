package com.sameer.framework.pages;

import com.sameer.framework.driver.DriverManager;
import com.sameer.framework.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {

    protected WebDriver driver;

    public BasePage(){
        driver = DriverManager.getDriver();
    }



    protected void click(By locator){
        WaitUtils.waitForElementToBeClickable(locator);
        DriverManager.getDriver().findElement(locator).click();
//        driver.findElement(locator).click();
    }

    protected void type(By locator, String text){
        WaitUtils.waitForElementToBeVisible(locator);
        driver.findElement(locator).sendKeys(text);
    }

    protected String getText(By locator){
        WaitUtils.waitForElementToBeVisible(locator);;
        return driver.findElement(locator).getText();
    }

}
//Why protected?
//👉 Accessible in child classes (LoginPage, ProductPage)
//👉 Not exposed publicly outside pages