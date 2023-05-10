package com.PlaceLab.HarisJasarevic.qamp.pages;

import net.datafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import java.util.Random;
import static java.util.Locale.US;

public class SinglePlaceReportPage {

    private WebDriver driver;
    Faker faker = new Faker(US);
    Random random = new Random();
    private final static String EXPECTED_PAGE_TITLE = "PlaceLab - demo";
    private final static By SEARCH_HEADER = By.xpath("//div[@class='report-header']");
    private final static By REPORT_FORM = By.id("single_poi_query");
    private final static By REPORT_NAME = By.id("name");
    private final static By SEARCH_FOR_PLACE = By.id("single_text");
    private final static By PHONE_NUMBER = By.id("single_phone");
    private final static By CATEGORY_DROPDOWN_BTN = By.xpath("//button[@class='multiselect dropdown-toggle btn btn-default']");
    private final static By CATEGORY_DROPDOWN_MENU = By.xpath("//ul[@class='multiselect-container dropdown-menu']");
    private final static By ENTERTAINMENT_SPORT = By.linkText("Entertainment > Sport");
    private final static By LOCATION_NAME = By.id("location_name");
    private final static By FOUND_LOCATION_NAME = By.xpath("//ul[@class='typeahead dropdown-menu']//li[@class='active']");
    private final static By LOCATION_BTN_SET = By.xpath("//div[@class='ui-dialog-buttonset']//button[@class='btn default-btn plab']");
    private final static By CREATE_REPORT_BTN = By.xpath("//button[@class='btn large-btn run-btn run-all-btn']");
    private final static String EXPECTED_REPORTS_PAGE_TITLE = "PlaceLab - demo";
    private final static By LANDED_REPORTS_PAGE = By.id("queries-nav-item");
    private final static By REPORT_PAGE_HEADER = By.linkText("Reports");
    private final static By REPORT_PAGE_TABLE = By.xpath("//div[@class='main-content']");

    public SinglePlaceReportPage(final WebDriver webDriver) {
        this.driver = webDriver;
    }

    public void validateSingleSearchReportContent () {
        final String actualPageTitle = driver.getTitle();
        final boolean isHeaderDisplayed = driver.findElement(SEARCH_HEADER).isDisplayed();
        final boolean isReportFormDisplayed = driver.findElement(REPORT_FORM).isDisplayed();
        Assert.assertEquals(actualPageTitle, EXPECTED_PAGE_TITLE, "Validate is page title correct");
        Assert.assertTrue(isHeaderDisplayed, "Validate is header displayed");
        Assert.assertTrue(isReportFormDisplayed, "Validate is report form displayed");
    }
    public void populateSingleSearchReportForm () {
        driver.findElement(REPORT_NAME).sendKeys("NY Report " + random.nextInt(9999));
        driver.findElement(SEARCH_FOR_PLACE).sendKeys("New York");
        driver.findElement(PHONE_NUMBER).sendKeys(faker.phoneNumber().cellPhone());
        final boolean isCategoryButtonDisplayed = driver.findElement(CATEGORY_DROPDOWN_BTN).isDisplayed();
        Assert.assertTrue(isCategoryButtonDisplayed, "Validate is category dropdown button displayed");
        driver.findElement(CATEGORY_DROPDOWN_BTN).click();
        final boolean isCategoryDropdownMenuDisplayed = driver.findElement(CATEGORY_DROPDOWN_MENU).isDisplayed();
        Assert.assertTrue(isCategoryDropdownMenuDisplayed, "Validate if category dropdown menu is displayed");
        driver.findElement(ENTERTAINMENT_SPORT).click();
        driver.findElement(LOCATION_NAME).sendKeys("350 Fifth Avenue, New York, NY 10118, USA");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(FOUND_LOCATION_NAME).click();
        final boolean isThisYourLocationButtonSetVisible = driver.findElement(LOCATION_BTN_SET).isDisplayed();
        Assert.assertTrue(isThisYourLocationButtonSetVisible, "Validate is your location button set visible");
        driver.findElement(LOCATION_BTN_SET).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        final boolean isCreateBtnDisplayed = driver.findElement(CREATE_REPORT_BTN).isDisplayed();
        Assert.assertTrue(isCreateBtnDisplayed, "Validate create button is visible and clickable");
        driver.findElement(CREATE_REPORT_BTN).click();
    }
    public void reportsPageContent () {
        final String actualReportsPageTitle = driver.getTitle();
        final boolean isReportPageLanded = driver.findElement(LANDED_REPORTS_PAGE).isDisplayed();
        final boolean isReportPageHeaderDisplayed = driver.findElement(REPORT_PAGE_HEADER).isDisplayed();
        final boolean isReportsPageTableDisplayed = driver.findElement(REPORT_PAGE_TABLE).isDisplayed();
        Assert.assertEquals(actualReportsPageTitle, EXPECTED_REPORTS_PAGE_TITLE, "Validate page title is correct");
        Assert.assertTrue(isReportPageLanded, "Validate that we landed on reports page");
        Assert.assertTrue(isReportPageHeaderDisplayed, "Validate reports page header is displayed");
        Assert.assertTrue(isReportsPageTableDisplayed, "Validate reports page table is displayed");
    }
}
