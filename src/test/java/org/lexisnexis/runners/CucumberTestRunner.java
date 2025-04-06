package org.lexisnexis.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features", // Path to feature files
        glue = "org.lexisnexis.stepdefinitions", // Step definitions package
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json",  // JSON output for Allure
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" // Corrected Allure plugin reference
        },
        monochrome = true,
        dryRun = false
)
public class CucumberTestRunner {
}
