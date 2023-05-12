package com.PlaceLab.HarisJasarevic.qamp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HomePage {

    private final WebDriver driver;
    private final static By USER_ROLE = By.id("user-role");
    private final static By DROPDOWN_BTN = By.cssSelector("#user-name > i");
    private final static By SIGN_OUT_BTN = By.linkText("Sign out");
    private final static By CREATE_MENU_DROPDOWN = By.id("create-menu");
    private final static By SINGLE_PLACE_SEARCH_BTN = By.id("singleplacesearch");

    public HomePage (final WebDriver webDriver) {
        this.driver = webDriver;
    }

    public void validateUserRole (final String expectedUserRole) {
        final String actualAdminUserRole = driver.findElement(USER_ROLE).getText();
        Assert.assertEquals(actualAdminUserRole, expectedUserRole, "Validate user role for the logged in user");
    }

    public void signOut () {
        Assert.assertTrue(driver.findElement(DROPDOWN_BTN).isDisplayed(), "Validate dropdown button is displayed");
        driver.findElement(DROPDOWN_BTN).click();
        Assert.assertTrue(driver.findElement(SIGN_OUT_BTN).isDisplayed(), "Validate sign out button is displayed");
        driver.findElement(SIGN_OUT_BTN).click();
    }

    public void navigationCreateReportMenu () {
        Assert.assertTrue(driver.findElement(CREATE_MENU_DROPDOWN).isDisplayed(), "Validate create menu dropdown is displayed");
        driver.findElement(CREATE_MENU_DROPDOWN).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(driver.findElement(SINGLE_PLACE_SEARCH_BTN).isDisplayed(), "Validate single place search button is displayed");
        driver.findElement(SINGLE_PLACE_SEARCH_BTN).click();
    }
}
