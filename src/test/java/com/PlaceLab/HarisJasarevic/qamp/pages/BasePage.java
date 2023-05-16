package com.PlaceLab.HarisJasarevic.qamp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected BasePage (final WebDriver webDriver) {
        this.driver = webDriver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    protected WebElement waitUntilClickable (final By element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement waitUntilClickable (final  WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitUntilUrlContains (final String fraction) {
        wait.until(ExpectedConditions.urlContains(fraction));
    }

    protected String getPageTitle() {
        return driver.getTitle();
    }

    protected String getPageUrl () {
        return driver.getCurrentUrl();
    }

}
