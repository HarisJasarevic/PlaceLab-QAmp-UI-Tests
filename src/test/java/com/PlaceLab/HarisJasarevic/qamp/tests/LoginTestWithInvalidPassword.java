package com.PlaceLab.HarisJasarevic.qamp.tests;

import com.PlaceLab.HarisJasarevic.qamp.pages.LoginPage;
import com.PlaceLab.HarisJasarevic.qamp.utils.WebDriverSetup;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.util.UUID;

public class LoginTestWithInvalidPassword {

    private WebDriver driver;
    private LoginPage loginPage;

    @Parameters("browser")
    @BeforeMethod
    public void setup(@Optional("chrome") final String browser) {
        driver = WebDriverSetup.getWebDriver(browser);
        driver.get("https://demo.placelab.com/");
        this.loginPage = new LoginPage(driver);
        driver.manage().window().maximize();
    }

    @Parameters("email")
    @Test (priority = 4, description = "Validate user is unable to login with invalid password")
    public void testInvalidPasswordLogin (final String email) {

        //Validate login page is open
        loginPage.validateLoginPageContent();

        //Populate login form with valid email and invalid password
        final String randomPassword = UUID.randomUUID().toString();
        loginPage.enterCredentials(email, randomPassword);
        loginPage.clickSubmitLoginButton();

        //Verify invalid credentials and failed login
        loginPage.verifyInvalidCredentials();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterMethod
    public void tearDown () {
        driver.close();
    }
}
