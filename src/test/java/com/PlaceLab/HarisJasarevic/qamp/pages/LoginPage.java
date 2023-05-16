package com.PlaceLab.HarisJasarevic.qamp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage {

    private final static By LOGIN_HEADER = By.cssSelector("div#login > p.headline");
    private final static By LOGIN_FORM = By.id("login_form");
    private final static String EXPECTED_PAGE_TITLE = "PlaceLab";
    private final static  By EMAIL_INPUT = By.id("email");
    private final static  By PASSWORD_INPUT = By.id("password");
    private final static  By LOGIN_SUBMIT_BTN = By.xpath("//input[@type='submit']");
    private final static By ACTUAL_ERROR_MSG = By.xpath("//div[@class='error-area']");
    private final static String EXPECTED_ERROR_MSG = "Invalid credentials!";

    private final WebDriver driver;
    private BasePage basePage;

    public LoginPage (final WebDriver webDriver) {
        this.driver = webDriver;
        this.basePage = new BasePage(driver);
    }

    public void validateLoginPageContent () {
        final String actualPageTitle = basePage.getPageTitle();
        final boolean isHeaderDisplayed = driver.findElement(LOGIN_HEADER).isDisplayed();
        final boolean isLoginFormDisplayed = driver.findElement(LOGIN_FORM).isDisplayed();
        Assert.assertTrue(isLoginFormDisplayed, "Validate is login form displayed");
        Assert.assertTrue(isHeaderDisplayed, "Validate is header text displayed");
        Assert.assertEquals(actualPageTitle, EXPECTED_PAGE_TITLE, "Validate page title is correct");
    }

    public void enterCredentials (final String email, final String password) {
        driver.findElement(EMAIL_INPUT).sendKeys(email);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
    }

    public void clearLoginFormInputs () {
        driver.findElement(EMAIL_INPUT).clear();
        driver.findElement(PASSWORD_INPUT).clear();
    }

    public void clickSubmitLoginButton () {
        driver.findElement(LOGIN_SUBMIT_BTN).click();
    }

    public void verifyInvalidCredentials () {
        final String actualErrorMessage = driver.findElement(ACTUAL_ERROR_MSG).getText();
        Assert.assertEquals(actualErrorMessage, EXPECTED_ERROR_MSG, "Validate is error message correct");
        System.out.println("Error message is: " + ACTUAL_ERROR_MSG);
        Assert.assertTrue(driver.findElement(LOGIN_FORM).isDisplayed(), "Validate login form is still displayed");
    }
}
