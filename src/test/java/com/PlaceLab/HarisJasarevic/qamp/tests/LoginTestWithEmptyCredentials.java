package com.PlaceLab.HarisJasarevic.qamp.tests;

import com.PlaceLab.HarisJasarevic.qamp.pages.LoginPage;
import com.PlaceLab.HarisJasarevic.qamp.utils.WebDriverSetup;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class LoginTestWithEmptyCredentials {

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

    @Test(priority = 5, description = "Validate user is not able to login with empty credentials")
    public void loginWithEmptyCredentials () {

        //Validate login page is open
        loginPage.validateLoginPageContent();

        //Populate login form
        loginPage.clearLoginFormInputs();
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
