package org.lexisnexis.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.lexisnexis.core.TestContext;
import org.lexisnexis.pages.exercise1.WebFormPage;
import org.openqa.selenium.WebDriver;

public class Auto1Definition {

      private final WebFormPage webFormPage;
      private WebDriver driver;

    public Auto1Definition() {
        this.driver = TestBase.driver;
        this.webFormPage = new WebFormPage(driver);
    }

    @Given("I open the web form page")
    public void iOpenTheWebFormPage() {
        driver.get(TestContext.get("baseUrl"));
    }

    @Then("I should see the page title {string}")
    public void iShouldSeeThePageTitle(String title) {
        Assert.assertEquals(webFormPage.pageTitleText(), title);
    }

    @When("I enter {string} in the text input field")
    public void iEnterInTheTextInputField(String text) {
        webFormPage.enterText(text);
    }

    @And("I enter {string} in the password input field")
    public void iEnterInThePasswordInputField(String password) {
        webFormPage.enterPassword(password);
    }

    @And("I enter {string} in the text area")
    public void iEnterInTheTextArea(String text) {
        webFormPage.enterTextarea(text);
    }

    @And("I select {string} from the dropdown")
    public void iSelectFromTheDropdown(String value) {
        webFormPage.selectDropdownOption(value);
    }

    @And("I choose {string} from the datalist dropdown")
    public void iChooseFromTheDatalistDropdown(String value) {
        webFormPage.enterDatalistOption(value);
    }

    @And("I upload the file {string}")
    public void iUploadTheFile(String filePath) {
        webFormPage.uploadFile(filePath);
    }

    @And("I check the checkbox")
    public void iCheckTheCheckbox() {
        webFormPage.toggleCheckbox();
    }

    @And("I uncheck the other checkbox")
    public void iUncheckTheOtherCheckbox() {
        webFormPage.toggleCheckbox();
    }

    @And("I click the default radio button")
    public void iSelectTheDefaultRadioButton() {
        webFormPage.selectRadioButton();
    }

    @And("I pick the color {string} from the color picker")
    public void iPickTheColorFromTheColorPicker(String hexCode) {
        webFormPage.pickColor(hexCode);
    }

    @And("I select {string} from the date picker")
    public void iSelectFromTheDatePicker(String date) throws Exception {
        try {
            webFormPage.pickDate(date);
        }
        catch (Exception e){throw new Exception();}
    }

    @And("I adjust the slider to {string}")
    public void iAdjustTheSliderTo(String range) {
        webFormPage.setRange(range);
    }

    @And("I click the submit button")
    public void iClickTheSubmitButton() {
        TestBase.takeScreenshot("Filled Form");
        webFormPage.submitForm();
    }

    @Then("I should see a confirmation message {string}")
    public void iShouldSeeAConfirmationMessage(String expectedMessage) {
        String actualMessage = webFormPage.getConfirmationMessage(); 
        Assert.assertEquals("Confirmation message mismatch!", expectedMessage, actualMessage);
  
    }
}
