package org.lexisnexis.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Step;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.lexisnexis.core.LexDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.lexisnexis.utils.ConfigReader;

public class TestBase {

    protected static WebDriver driver;

    @Step("Initializing test environment")
    @Before
    public void setUp() {
        // Ensure WebDriver is initialized first
        LexDriver.initDriver();
        driver = LexDriver.getDriver();

        // Load the base URL from config.properties
        String baseUrl = ConfigReader.getBaseUrl();
        if (baseUrl == null || baseUrl.isEmpty()) {
            throw new IllegalStateException("Base URL is missing in config.properties!");
        }
        System.out.println("Navigating to base URL: " + baseUrl);
        driver.get(baseUrl);
    }

    @Step("Closing app, ending test")
    @After
    public void tearDown(Scenario scenario) {
        // Quit the driver after test execution
        if (scenario.isFailed()) { // Check if step failed
            takeScreenshot(scenario.getName()); // Save screenshot with scenario name
        }
        LexDriver.quitDriver();
    }

    public static void takeScreenshot(String filename) {
        try {
            Path screenshotDir = Paths.get("screenshots");
            if (!Files.exists(screenshotDir)) {
                Files.createDirectories(screenshotDir); // Creates the folder if it doesn’t exist
            }

            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(screenshot.toPath(), screenshotDir.resolve(filename + ".png"));
            System.out.println("Screenshot saved: screenshots/" + filename + ".png");
        } catch (Exception e) {
            System.out.println("Error taking screenshot: " + e.getMessage());
        }
    }
}
