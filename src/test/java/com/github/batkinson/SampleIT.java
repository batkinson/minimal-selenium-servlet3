package com.github.batkinson;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertTrue;

public class SampleIT {

    private String appUrl;
    private File screenshotDir;

    WebDriver driver;

    @Parameters({"appUrl", "screenshotDir"})
    public SampleIT(String appUrl, String screenshotDir) {
        this.appUrl = appUrl;
        this.screenshotDir = new File(screenshotDir);
        this.screenshotDir.mkdirs();
    }

    @BeforeClass
    public void createDriver() {
        driver = new FirefoxDriver();
    }

    @Test
    public void testHello() {
        driver.get(appUrl);
        assertTrue(driver.getPageSource().contains("Hello!"), "Body should contain text 'Hello'");
    }

    @AfterMethod
    public void takeScreenshot(ITestResult result) throws IOException {
        String successStatus = result.isSuccess() ? "SUCCESS" : "NOSUCCESS";
        String shotName = String.format("%s-%s-%s.png", successStatus, result.getTestClass().getName(), result.getName());
        File finalShotFile = new File(screenshotDir, shotName);
        File shotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(shotFile, finalShotFile);
    }

    @AfterClass
    public void quitDriver() {
        driver.quit();
    }

}
