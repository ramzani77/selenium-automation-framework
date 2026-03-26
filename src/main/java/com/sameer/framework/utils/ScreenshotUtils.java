package com.sameer.framework.utils;

import com.sameer.framework.driver.DriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {
    @Attachment(value = "Failure Screenshot", type = "image/png")
    public static byte[] attachScreenshot(String path) throws IOException {
        return Files.readAllBytes(Paths.get(path));
    }
    public static String captureScreenshot(String testName) {

        File src = ((TakesScreenshot) DriverManager.getDriver())
                .getScreenshotAs(OutputType.FILE);

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String path = "screenshots/" + testName + "_" + timestamp + ".png";

        File dest = new File(path);

        try {
            FileHandler.copy(src, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }
}