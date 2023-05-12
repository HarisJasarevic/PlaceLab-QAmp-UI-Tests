package com.PlaceLab.HarisJasarevic.qamp.tests;

import com.PlaceLab.HarisJasarevic.qamp.pages.HomePage;
import com.PlaceLab.HarisJasarevic.qamp.pages.LoginPage;
import com.PlaceLab.HarisJasarevic.qamp.pages.SinglePlaceReportPage;
import com.PlaceLab.HarisJasarevic.qamp.utils.WebDriverSetup;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class SinglePlaceSearchReport {

    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private SinglePlaceReportPage singlePlaceReportPage;

    @Parameters("browser")
    @BeforeMethod
    public void setup(@Optional("chrome") final String browser) {
        driver = WebDriverSetup.getWebDriver(browser);
        driver.get("https://demo.placelab.com/");
        this.loginPage = new LoginPage(driver);
        this.homePage = new HomePage(driver);
        this.singlePlaceReportPage = new SinglePlaceReportPage(driver);
        driver.manage().window().maximize();
    }

    @Parameters( { "email", "password" } )
    @Test(priority = 6, description = "Login with valid credentials and create Single place report")
    public void singlePlaceSearchReport (final String email, final String password) {

        final String expectedUserRole = "Group Admin";

        //Validate login page is open
        loginPage.validateLoginPageContent();

        //Populate login form
        loginPage.enterCredentials(email, password);
        loginPage.clickSubmitLoginButton();

        //Verify user role
        homePage.validateUserRole(expectedUserRole);

        //Validate create menu is displayed and enter single place search
        homePage.navigationCreateReportMenu();

        //Validate single search report page content
        singlePlaceReportPage.validateSingleSearchReportContent();

        //Populate single search report form
        singlePlaceReportPage.populateSingleSearchReportForm();

        //Validate Reports page content
        singlePlaceReportPage.validateReportsPageContent();
    }

    @AfterMethod
    public void tearDown () {
        driver.close();
    }
}
