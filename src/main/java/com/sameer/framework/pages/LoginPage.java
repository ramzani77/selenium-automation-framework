package com.sameer.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.sameer.framework.driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.qameta.allure.Step;

public class LoginPage extends BasePage {

    private static final Logger log = LogManager.getLogger(LoginPage.class);

    private WebDriver driver;

    public LoginPage() {
        this.driver = DriverManager.getDriver();
    }

    private By username = By.id("user-name");
    private By password = By.id("password");
    private By loginBtn = By.id("login-button");
    private By errorMessage = By.cssSelector("h3[data-test='error']");

    // 🔥 Step 1
    @Step("Enter username: {0}")
    public LoginPage enterUsername(String user) {
        log.info("Entering username");
        type(username, user);
        return this;
    }

    // 🔥 Step 2
    @Step("Enter password")
    public LoginPage enterPassword(String pass) {
        log.info("Entering password");
        type(password, pass);
        return this;
    }

    // 🔥 Step 3
    @Step("Click login button")
    public LoginPage clickLoginButton() {
        log.info("Clicking login button");
        click(loginBtn);
        return this;
    }

    // 🔥 Step 4
    @Step("Verify error message is displayed")
    public boolean isErrorDisplayed() {
        try {
            boolean visible = driver.findElement(errorMessage).isDisplayed();
            log.info("Is error displayed: " + visible);
            return visible;
        } catch (Exception e) {
            log.error("Error message not displayed");
            return false;
        }
    }

    // 🔥 MASTER STEP (VERY IMPORTANT)
    @Step("Login with username: {0} and password: {1}")
    public LoginPage login(String user, String pass) {
        enterUsername(user);
        enterPassword(pass);
        clickLoginButton();
        return this;
    }
}