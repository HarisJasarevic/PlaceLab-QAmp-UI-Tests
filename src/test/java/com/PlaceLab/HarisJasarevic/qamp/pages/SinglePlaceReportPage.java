package com.PlaceLab.HarisJasarevic.qamp.pages;

import net.datafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;
import java.util.Random;


import static java.util.Locale.US;

public class SinglePlaceReportPage extends BasePage {

    private static WebDriverWait wait;
    private WebDriver driver;
    private QueriesPage queriesPage;
    private final Faker faker = new Faker(US);
    private final Random random = new Random();
    private String reportID;
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
    private final static By DELETE_ICON = By.cssSelector("#action-delete > a > 1");
    private final static By REPORT_CHECKBOXES = By.cssSelector("#table_queries > tbody > tr > td.large > div");
    private final static By CONFIRM_DELETE_REPORT = By.xpath("//*[@id='confirm-link']");


    public SinglePlaceReportPage(final WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.queriesPage = new QueriesPage(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    public void validateSingleSearchReportContent() {
        final String actualPageTitle = getPageTitle();
        final boolean isHeaderDisplayed = driver.findElement(SEARCH_HEADER).isDisplayed();
        final boolean isReportFormDisplayed = driver.findElement(REPORT_FORM).isDisplayed();
        Assert.assertEquals(actualPageTitle, EXPECTED_PAGE_TITLE, "Validate is page title correct");
        Assert.assertTrue(isHeaderDisplayed, "Validate is header displayed");
        Assert.assertTrue(isReportFormDisplayed, "Validate is report form displayed");
    }

    public String populateSingleSearchReportForm() {
        driver.findElement(REPORT_NAME).sendKeys("NY Report " + random.nextInt(9999));
        driver.findElement(SEARCH_FOR_PLACE).sendKeys("New York");
        driver.findElement(PHONE_NUMBER).sendKeys(faker.phoneNumber().phoneNumber().replace(".", "").replace(",", "").trim());
        final boolean isCategoryButtonDisplayed = driver.findElement(CATEGORY_DROPDOWN_BTN).isDisplayed();
        Assert.assertTrue(isCategoryButtonDisplayed, "Validate is category dropdown button displayed");
        driver.findElement(CATEGORY_DROPDOWN_BTN).click();
        final boolean isCategoryDropdownMenuDisplayed = driver.findElement(CATEGORY_DROPDOWN_MENU).isDisplayed();
        Assert.assertTrue(isCategoryDropdownMenuDisplayed, "Validate if category dropdown menu is displayed");
        driver.findElement(ENTERTAINMENT_SPORT).click();
        driver.findElement(LOCATION_NAME).sendKeys("350 Fifth Avenue, New York, NY 10118, USA");
        waitUntilClickable(FOUND_LOCATION_NAME).click();
        final boolean isThisYourLocationButtonSetVisible = driver.findElement(LOCATION_BTN_SET).isDisplayed();
        Assert.assertTrue(isThisYourLocationButtonSetVisible, "Validate is your location button set visible");
        driver.findElement(LOCATION_BTN_SET).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(CREATE_REPORT_BTN)));
        final boolean isCreateBtnDisplayed = driver.findElement(CREATE_REPORT_BTN).isDisplayed();
        Assert.assertTrue(isCreateBtnDisplayed, "Validate create button is visible and clickable");
        waitUntilClickable(CREATE_REPORT_BTN).click();
        reportID = driver.getCurrentUrl().substring(driver.getCurrentUrl().lastIndexOf("/") + 1);
        return reportID;
}

    public void deleteReport( final String reportID ) {
        waitUntilClickable(getReportCheckbox(reportID)).click();
        waitUntilClickable(DELETE_ICON).click();
        waitUntilClickable(CONFIRM_DELETE_REPORT).click();
    }

    private WebElement getReportCheckbox (final String reportID) {
        final List<WebElement> checkboxes = driver.findElements(REPORT_CHECKBOXES);
        for (final WebElement checkbox : checkboxes) {
            if (checkbox.findElement(By.tagName("input")).getAttribute("value").equals(reportID)) {
                return checkbox;
            }
        }
        throw new NotFoundException();
    }
}
