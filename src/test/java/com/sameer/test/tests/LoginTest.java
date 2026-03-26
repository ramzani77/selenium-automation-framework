package com.sameer.test.tests;
import com.sameer.framework.pages.LoginPage;
import com.sameer.test.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;


public class LoginTest extends BaseTest {

    @Test
    public void verifyInvalidLogin(){
        LoginPage loginPage = new LoginPage();

        loginPage.enterUsername("Invalid_Username")
                .enterPassword("Invalid_Password")
                .clickLoginButton();

        Assert.assertTrue(loginPage.getErrorMessage().contains("Epic sadface"));
    }

    @Test
    public void verifyInvalidLogin2() {
        LoginPage loginPage = new LoginPage();

        loginPage.enterUsername("invalid_user")
                .enterPassword("invalid_pass")
                .clickLoginButton();

        Assert.assertTrue(loginPage.isErrorDisplayed());
    }
    @Test
    public void retryTest() {
        Assert.assertTrue(true);
    }
}
