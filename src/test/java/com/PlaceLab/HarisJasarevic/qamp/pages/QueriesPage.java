package com.PlaceLab.HarisJasarevic.qamp.pages;

import com.PlaceLab.HarisJasarevic.qamp.utils.GlobalValues;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class QueriesPage extends BasePage {

    private WebDriver driver;
    private static WebDriverWait wait;
    private final static String EXPECTED_REPORTS_PAGE_TITLE = "PlaceLab - demo";
    private final static By LANDED_REPORTS_PAGE = By.id("queries-nav-item");
    private final static By REPORT_PAGE_HEADER = By.linkText("Reports");
    private final static By REPORT_PAGE_TABLE = By.xpath("//div[@class='main-content']");


    public QueriesPage (final WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void validateQueriesPageIsOpen () {
        wait.until(ExpectedConditions.urlToBe(GlobalValues.QUERIES_URL));
        final String actualReportsPageTitle = getPageTitle();
        final boolean isReportPageLanded = driver.findElement(LANDED_REPORTS_PAGE).isDisplayed();
        final boolean isReportPageHeaderDisplayed = driver.findElement(REPORT_PAGE_HEADER).isDisplayed();
        final boolean isReportsPageTableDisplayed = driver.findElement(REPORT_PAGE_TABLE).isDisplayed();
        Assert.assertEquals(actualReportsPageTitle, EXPECTED_REPORTS_PAGE_TITLE, "Validate page title is correct");
        Assert.assertTrue(isReportPageLanded, "Validate that we landed on reports page");
        Assert.assertTrue(isReportPageHeaderDisplayed, "Validate reports page header is displayed");
        Assert.assertTrue(isReportsPageTableDisplayed, "Validate reports page table is displayed");
    }
}
