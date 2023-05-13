package com.PlaceLab.HarisJasarevic.qamp.utils;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    protected WebDriver driver;

    @Parameters ("browser")
    @BeforeMethod (alwaysRun = true)
    public void setupDriver (@Optional ("chrome") final String browser) {
        this.driver = WebDriverSetup.getWebDriver(browser);
        driver.get(GlobalValues.HOST);
    }

    @AfterMethod (alwaysRun = true)
    public void teardown () {
        this.driver.close();
    }
}
