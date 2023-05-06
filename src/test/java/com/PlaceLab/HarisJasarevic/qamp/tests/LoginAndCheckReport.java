package com.PlaceLab.HarisJasarevic.qamp.tests;

import com.PlaceLab.HarisJasarevic.qamp.utils.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginAndCheckReport {

    private WebDriver driver;

    @Parameters("browser")
    @BeforeTest
    public void setup(@Optional("chrome") final String browser) {
        driver = WebDriverSetup.getWebDriver(browser);
        driver.get("https://demo.placelab.com/");
        driver.manage().window().maximize();
        System.out.println("Opened browser: " + browser);
    }

    @Parameters( { "email", "password" } )
    @Test
    public void testLoginAndReportCheck (final String email, final String password) {
        final String actualPageTitle = driver.getTitle();
        final String expectedPageTitle = "PlaceLab";
        final String expectedAdminUserRole = "Group Admin";
        final String expectedNavbarReportsText = "Reports";
        final String expectedNewYorkReportPageTitle = "PlaceLab - New York, NY, USA, 49";

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
        System.out.println("Page title is: " + actualPageTitle);

        //Populate login form
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        //Verify user role
        final String actualAdminUserRole = driver.findElement(By.id("user-role")).getText();
        Assert.assertEquals(actualAdminUserRole, expectedAdminUserRole, "Validate user role for the logged in user");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Assert.assertTrue(
                driver.findElement(By.id("queries-nav-item")).isDisplayed(),
                "Validate that Reports tab is displayed"
        );

        //Open reports tab
        driver.findElement(By.id("queries-nav-item")).click();

        //Validate that reports tab is opened
        final String actualNavbarReportsText = driver.findElement(By.linkText("Reports")).getText();
        Assert.assertEquals(actualNavbarReportsText, expectedNavbarReportsText, "Validate that currently you are in Reports section of PlaceLab");
        System.out.println(actualNavbarReportsText);

        Assert.assertTrue(
            driver.findElement(By.id("request_180320")).isDisplayed(),
            "Validate that report 'New York, NY, USA, 49' is displayed"
        );

        //Open report New York, NY, USA, 49
        driver.findElement(By.id("request_180320")).click();

        //Validate New York , NY, USA, 49 report is opened
        final String actualNewYorkReportPageTitle = driver.getTitle();
        Assert.assertEquals(actualNewYorkReportPageTitle, expectedNewYorkReportPageTitle, "Validate New York report page title");
        System.out.println(actualPageTitle);
    }
    @AfterTest
    public void tearDown () {
        driver.close();
    }
}
