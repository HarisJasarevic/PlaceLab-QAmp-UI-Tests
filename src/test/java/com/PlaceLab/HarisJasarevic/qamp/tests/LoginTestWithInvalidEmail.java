package com.PlaceLab.HarisJasarevic.qamp.tests;

import com.PlaceLab.HarisJasarevic.qamp.pages.LoginPage;
import com.PlaceLab.HarisJasarevic.qamp.utils.BaseTest;
import com.PlaceLab.HarisJasarevic.qamp.utils.WebDriverSetup;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class LoginTestWithInvalidEmail extends BaseTest {

    private final Faker faker = new Faker();
    private LoginPage loginPage;

    @Parameters("browser")
    @BeforeMethod (alwaysRun = true)
    public void initPages () {
        this.loginPage = new LoginPage(driver);
        driver.manage().window().maximize();
    }

    @Parameters("password")
    @Test (priority = 3, description = "Validate user is unable to login with invalid email")
    public void testInvalidEmailLogin (final String password) {

        final String expectedErrorMessage = "Invalid credentials!";

        //Validate login page is open
        loginPage.validateLoginPageContent();

        //Populate login form with invalid email and correct password
        final String randomEmail = faker.internet().emailAddress().toString();
        loginPage.enterCredentials(randomEmail, password);
        loginPage.clickSubmitLoginButton();

        //Verify invalid credentials and failed login
        loginPage.verifyInvalidCredentials();
    }
}
