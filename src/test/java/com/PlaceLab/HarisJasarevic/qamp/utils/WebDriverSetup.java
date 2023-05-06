package com.PlaceLab.HarisJasarevic.qamp.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class WebDriverSetup {

    public static WebDriver getWebDriver (final String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                return getChromeDriver();
            case "edge":
                return getEdgeDriver();
            default:
                throw new IllegalArgumentException("Match case not found for browser name: " + browserName);
        }
    }
    private static WebDriver getChromeDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    private static WebDriver getEdgeDriver () {
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }
}
