package com.PlaceLab.HarisJasarevic.qamp.tests;

import com.PlaceLab.HarisJasarevic.qamp.utils.WebDriverSetup;
import net.bytebuddy.build.Plugin;
import org.apache.hc.core5.reactor.Command;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;


public class SmokeTest {

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
    public void testPositiveLogin (final String email, final String password) {
        final String actualPageTitle = driver.getTitle();
        final String expectedPageTitle = "PlaceLab";
        final String expectedAdminUserRole = "Group Admin";

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

        //Sign out
        driver.findElement(By.cssSelector("#user-name > i")).click();
        Assert.assertTrue(
                driver.findElement(By.cssSelector("#user-name > i")).isDisplayed(),
                "Validate sign out button is displayed");
        driver.findElement(By.linkText("Sign out")).click();
    }

    @AfterTest
    public void teardown () {
        driver.close();
    }
}
