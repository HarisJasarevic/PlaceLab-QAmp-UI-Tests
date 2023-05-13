package com.PlaceLab.HarisJasarevic.qamp.tests;

import com.PlaceLab.HarisJasarevic.qamp.pages.NavigationPage;
import com.PlaceLab.HarisJasarevic.qamp.pages.LoginPage;
import com.PlaceLab.HarisJasarevic.qamp.utils.BaseTest;
import org.testng.annotations.*;

public class LoginWithValidCredentials extends BaseTest {

    private LoginPage loginPage;
    private NavigationPage navigationPage;

    @Parameters("browser")
    @BeforeMethod (alwaysRun = true)
    public void initPages() {
        this.loginPage = new LoginPage(driver);
        this.navigationPage = new NavigationPage(driver);
        driver.manage().window().maximize();
    }

    @Parameters( { "email", "password" } )
    @Test (priority = 1, description = "Validate user is able to login with valid credentials")
    public void testPositiveLogin (final String email, final String password) {

        final String expectedUserRole = "Group Admin";

        //Validate login page is open
        loginPage.validateLoginPageContent();

        //Populate login form
        loginPage.enterCredentials(email, password);
        loginPage.clickSubmitLoginButton();

        //Verify user role
        navigationPage.validateUserRole(expectedUserRole);

        //Sign out
        navigationPage.signOut();
    }
}
