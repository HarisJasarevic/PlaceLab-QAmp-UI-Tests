package com.PlaceLab.HarisJasarevic.qamp.tests;

import com.PlaceLab.HarisJasarevic.qamp.pages.HomePage;
import com.PlaceLab.HarisJasarevic.qamp.pages.LoginPage;
import com.PlaceLab.HarisJasarevic.qamp.utils.WebDriverSetup;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;


public class LoginWithValidCredentials {

    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;

    @Parameters("browser")
    @BeforeMethod
    public void setup(@Optional("chrome") final String browser) {
        driver = WebDriverSetup.getWebDriver(browser);
        driver.get("https://demo.placelab.com/");
        this.loginPage = new LoginPage(driver);
        this.homePage = new HomePage(driver);
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
        homePage.validateUserRole(expectedUserRole);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Sign out
        homePage.signOut();
    }

    @AfterMethod
    public void teardown () {
        driver.close();
    }
}
