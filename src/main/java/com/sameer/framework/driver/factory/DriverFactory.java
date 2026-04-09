package com.sameer.framework.driver.factory;

import com.sameer.framework.driver.DriverManager;
import com.sameer.framework.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeOptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;

public class DriverFactory {

    private static final Logger log = LogManager.getLogger(DriverFactory.class);

    public static void initDriver(String browser) {

        if (browser == null) {
            throw new RuntimeException("Browser is not provided");
        }

        String runMode = ConfigReader.get("runMode");

        log.info("Run Mode: " + runMode);
        log.info("Browser from TestNG: " + browser);

        WebDriver driver;

        if ("remote".equalsIgnoreCase(runMode)) {

            try {
                log.info("Attempting Remote execution (Grid)");
                driver = initRemoteDriver(browser);

            } catch (Exception e) {

                log.error("Grid not available. Falling back to LOCAL execution");

                driver = initLocalDriver(browser);
            }

        } else {
            driver = initLocalDriver(browser);
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        DriverManager.setDriver(driver);
        log.info("Browser launched successfully");
    }

    // ================= LOCAL =================
    private static WebDriver initLocalDriver(String browser) {

        switch (browser.toLowerCase()) {

            case "chrome":
                log.info("Launching Chrome (Local)");
                WebDriverManager.chromedriver().setup();

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=new");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");

                return new ChromeDriver(options);

            case "firefox":
                log.info("Launching Firefox (Local)");
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();

            case "edge":
                log.info("Launching Edge (Local)");
                return new EdgeDriver();

            default:
                throw new RuntimeException("Invalid browser: " + browser);
        }
    }

    // ================= REMOTE (DOCKER / GRID) =================
    private static WebDriver initRemoteDriver(String browser) {

        String gridUrl = ConfigReader.get("gridUrl");
        if (gridUrl == null || gridUrl.isEmpty()) {
            throw new RuntimeException("Grid URL is not defined in config.properties");
        }

        try {

            switch (browser.toLowerCase()) {

                case "chrome":
                    log.info("Launching Chrome (Remote - Docker/Grid)");
                    ChromeOptions chromeOptions = new ChromeOptions();
                    return new RemoteWebDriver(new URL(gridUrl), chromeOptions);

                case "firefox":
                    log.info("Launching Firefox (Remote - Docker/Grid)");
                    return new RemoteWebDriver(new URL(gridUrl), new FirefoxOptions());

                case "edge":
                    log.info("Launching Edge (Remote - Docker/Grid)");
                    return new RemoteWebDriver(new URL(gridUrl), new EdgeOptions());

                default:
                    throw new RuntimeException("Invalid browser: " + browser);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to Grid: " + gridUrl, e);
        }
    }
}