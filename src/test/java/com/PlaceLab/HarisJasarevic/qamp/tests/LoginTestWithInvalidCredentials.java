package com.PlaceLab.HarisJasarevic.qamp.tests;

import com.PlaceLab.HarisJasarevic.qamp.utils.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTestWithInvalidCredentials {

    private WebDriver driver;

    @Parameters("browser")
    @BeforeTest
    public void setup(@Optional("chrome") final String browser) {
        driver = WebDriverSetup.getWebDriver(browser);
        driver.get("https://demo.placelab.com/");
        driver.manage().window().maximize();
    }

    @Test
    public void testInvalidCredentialsLogin () {

        final String actualPageTitle = driver.getTitle();
        final String expectedPageTitle = "PlaceLab";
        final String expectedErrorMessage = "Invalid credentials!";

        //Validate login page is open
        final boolean isHeaderDisplayed = driver.findElement(By.cssSelector("div#login > p.headline")).isDisplayed();
        Assert.assertTrue(isHeaderDisplayed);
        Assert.assertTrue(
                driver.findElement(By.id("login_form")).isDisplayed(),
                "Validate login form is displayed"
        );
        Assert.assertTrue(
                driver.findElement(By.cssSelector("div#login > p.headline")).isDisplayed(),
                "Validate header text is displayed"
        );

        Assert.assertEquals(actualPageTitle, expectedPageTitle);

        //Populate login form with invalid email and password
        driver.findElement(By.id("email")).sendKeys("test@testmail.com");
        driver.findElement(By.id("password")).sendKeys("Testpassword123!");
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        //Verify invalid credentials and failed login
        final String actualErrorMessage = driver.findElement(By.xpath("//div[@class='error-area']")).getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
        System.out.println("Error message is: " + actualErrorMessage);
        Assert.assertTrue(
            driver.findElement(By.id("login_form")).isDisplayed(),
            "Validate login form is still displayed"
        );
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterTest
    public void tearDown () {
        driver.close();
    }
}
