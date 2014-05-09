package com.github.batkinson;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class SampleIT {

    WebDriver driver;

    @BeforeClass
    public void createDriver() {
        driver = new FirefoxDriver();
    }

    @Test
    @Parameters("appUrl")
    public void testHello(String appUrl) {
        driver.get(appUrl);
        assertTrue(driver.getPageSource().contains("Hello!"), "Body should contain text 'Hello'");
    }

    @AfterClass
    public void quitDriver() {
        driver.quit();
    }
}
