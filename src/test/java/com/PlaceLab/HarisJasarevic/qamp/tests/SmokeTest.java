package com.PlaceLab.HarisJasarevic.qamp.tests;

import com.PlaceLab.HarisJasarevic.qamp.utils.WebDriverSetup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SmokeTest {

    private WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = WebDriverSetup.getWebDriver(System.getProperty("browser"));
        driver.get("https://demo.placelab.com/");
        System.out.println("Opened browser");
    }

    @Test
    public void openPage() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterTest
    public void teardown () {
        System.out.println("Opened browser: " + System.getProperty("browser"));
        driver.close();
    }

    @Test
    public void pageTitle () {
        String title = driver.getTitle();
        System.out.println("Page title is: " + title);
    }
    private void setUpChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
        this.driver = new ChromeDriver();
    }
}
