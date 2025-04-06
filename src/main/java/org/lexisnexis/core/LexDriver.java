package org.lexisnexis.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.chrome.ChromeOptions;
import org.lexisnexis.utils.ConfigReader;
import java.io.File;
import java.time.Duration;

public class LexDriver {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver() {
        if (driver.get() == null) {
            ConfigReader configReader = new ConfigReader();  // Ensure instance is available
            String browser = configReader.getBrowser().toLowerCase();
            String driverPath = configReader.getDriverPath(" ");

//            if (browser.isEmpty() || driverPath.isEmpty()) {
//                throw new RuntimeException("Browser or driver path is not set in config.properties!");
//            }

            // Log the selected browser and driver path
            System.out.println("Initializing driver for browser: " + browser);
            System.out.println("Driver path: " + driverPath);

            // Initialize WebDriver based on the selected browser
            switch ("edge") {
                case "firefox":
                    System.setProperty("webdriver.gecko.driver", driverPath);
                    driver.set(new FirefoxDriver());
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--start-maximized");
                    driver.set(new EdgeDriver());
                    break;
                default:
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--start-maximized");
                    driver.set(new ChromeDriver(options));
                    break;
            }

            // Set timeout and maximize window
            int timeout = configReader.getTimeout();
            driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
            driver.get().manage().window().maximize();
        }
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            throw new IllegalStateException("Driver not initialized. Call initDriver() first.");
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
