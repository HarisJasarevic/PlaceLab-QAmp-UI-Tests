package com.PlaceLab.HarisJasarevic.qamp.tests;

import com.PlaceLab.HarisJasarevic.qamp.pages.LoginPage;
import com.PlaceLab.HarisJasarevic.qamp.utils.BaseTest;
import com.PlaceLab.HarisJasarevic.qamp.utils.WebDriverSetup;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.util.UUID;

public class LoginTestWithInvalidCredentials extends BaseTest {

    final Faker faker = new Faker();
    private LoginPage loginPage;

    @Parameters("browser")
    @BeforeMethod (alwaysRun = true)
    public void initPages () {
        this.loginPage = new LoginPage(driver);
        driver.manage().window().maximize();
    }

    @Test (priority = 2, description = "Validate user is unable to login with invalid credentials")
    public void testInvalidCredentialsLogin () {

        //Validate login page is open
        loginPage.validateLoginPageContent();

        //Populate login form with invalid email and password
        final String randomEmail = faker.internet().emailAddress().toString();
        final String randomPassword = UUID.randomUUID().toString();
        loginPage.enterCredentials(randomEmail, randomPassword);
        loginPage.clickSubmitLoginButton();

        //Verify invalid credentials and failed login
        loginPage.verifyInvalidCredentials();
    }
}
