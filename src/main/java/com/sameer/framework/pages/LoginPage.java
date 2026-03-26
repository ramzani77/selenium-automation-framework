package com.sameer.framework.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.sameer.framework.driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


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


    //Fluent Design Pattern.
    public LoginPage enterUsername(String user){
        log.info("Entering username");
        type(username, user);
        return this;
    }
     public LoginPage enterPassword(String pass){
         log.info("Entering password");
         type(password,pass);
        return this;
     }
     public LoginPage clickLoginButton(){
         log.info("Clicking login button");
        click(loginBtn);
        return this;
     }

     public String getErrorMessage(){
         String error = getText(errorMessage);
         log.info("Error message displayed: " + error);
         return error;
     }

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

}
