package com.sameer.test.tests;

import com.sameer.framework.pages.LoginPage;
import com.sameer.test.base.BaseTest;
import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Authentication Module")
@Feature("Login Feature")
@Owner("Sameer")
@Tag("Regression")
public class LoginTest extends BaseTest {

    @Test
    @Description("Verify login with invalid credentials and check error message")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Invalid Login Scenario")
    @Link(name = "Website", url = "https://www.saucedemo.com")
    @Issue("BUG-101")
    @TmsLink("TC-LOGIN-001")
    public void verifyInvalidLogin() {

        LoginPage loginPage = new LoginPage();

        loginPage.login("Invalid_Username", "Invalid_Password");

        Assert.assertTrue(loginPage.isErrorDisplayed());
    }

    @Test(description = "Verify login with invalid credentials 2")
    @Severity(SeverityLevel.NORMAL)
    @Story("Invalid Login Scenario - Negative Test")
    @Tag("Smoke")
    @Issue("BUG-102")
    @TmsLink("TC-LOGIN-002")
    public void verifyInvalidLogin2() {

        LoginPage loginPage = new LoginPage();

        loginPage.login("invalid_user", "invalid_pass");

        // Intentional failure
        Assert.assertTrue(false);
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @Story("Retry mechanism validation")
    @Tag("Sanity")
    @TmsLink("TC-RETRY-001")
    public void retryTest() {
        Assert.assertTrue(true);
    }
}