package com.PlaceLab.HarisJasarevic.qamp.tests;

import com.PlaceLab.HarisJasarevic.qamp.pages.LoginPage;
import com.PlaceLab.HarisJasarevic.qamp.utils.BaseTest;
import com.PlaceLab.HarisJasarevic.qamp.utils.WebDriverSetup;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import java.util.UUID;

public class LoginTestWithInvalidPassword extends BaseTest {

    private LoginPage loginPage;

    @Parameters("browser")
    @BeforeMethod (alwaysRun = true)
    public void initPages () {
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
    }
}
