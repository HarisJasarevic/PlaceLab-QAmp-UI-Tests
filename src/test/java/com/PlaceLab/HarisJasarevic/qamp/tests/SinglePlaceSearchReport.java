package com.PlaceLab.HarisJasarevic.qamp.tests;

import com.PlaceLab.HarisJasarevic.qamp.pages.NavigationPage;
import com.PlaceLab.HarisJasarevic.qamp.pages.LoginPage;
import com.PlaceLab.HarisJasarevic.qamp.pages.QueriesPage;
import com.PlaceLab.HarisJasarevic.qamp.pages.SinglePlaceReportPage;
import com.PlaceLab.HarisJasarevic.qamp.utils.BaseTest;
import org.testng.ITestContext;
import org.testng.annotations.*;

public class SinglePlaceSearchReport extends BaseTest {

    private LoginPage loginPage;
    private NavigationPage navigationPage;
    private SinglePlaceReportPage singlePlaceReportPage;
    private QueriesPage queriesPage;

    @Parameters("browser")
    @BeforeMethod (alwaysRun = true)
    public void setup(@Optional("chrome") final String browser) {
        this.loginPage = new LoginPage(driver);
        this.navigationPage = new NavigationPage(driver);
        this.singlePlaceReportPage = new SinglePlaceReportPage(driver);
        this.queriesPage = new QueriesPage(driver);
        driver.manage().window().maximize();
    }

    @Parameters( { "email", "password" } )
    @Test(priority = 6, description = "Login with valid credentials and create Single place report")
    public void singlePlaceSearchReport (final String email, final String password, final ITestContext context) {

        final String expectedUserRole = "Group Admin";

        //Validate login page is open
        loginPage.validateLoginPageContent();

        //Populate login form
        loginPage.enterCredentials(email, password);
        loginPage.clickSubmitLoginButton();

        //Verify user role
        navigationPage.validateUserRole(expectedUserRole);

        //Validate create menu is displayed in navigation and enter single place search
        navigationPage.navigationCreateReportMenu();

        //Validate single search report page content
        singlePlaceReportPage.validateSingleSearchReportContent();

        //Populate single search report form
        singlePlaceReportPage.populateSingleSearchReportForm();

        //Validate Reports page content
        queriesPage.validateQueriesPageIsOpen();

        //Delete created report
        singlePlaceReportPage.deleteReport("reportID");


    }

}
