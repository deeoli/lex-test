package org.lexisnexis.pages.exercise1;

import org.lexisnexis.pages.PageBase;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.nio.file.Paths;

public class WebFormPage extends PageBase {

    // Elements
    @FindBy(xpath = "//h1") private WebElement pageTitle;
    @FindBy(id = "my-text-id") private WebElement textInput;
    @FindBy(name = "my-password") private WebElement passwordInput;
    @FindBy(name = "my-textarea") private WebElement textareaInput;
    @FindBy(name = "my-select") private WebElement dropdownSelect;
    @FindBy(name = "my-datalist") private WebElement datalistInput;
    @FindBy(xpath = "//input[@type='file']") private WebElement fileInput;
    @FindBy(id = "my-check-1") private WebElement checkedCheckbox;
    @FindBy(id = "my-check-2") private WebElement defaultCheckbox;
    @FindBy(id = "my-radio-1") private WebElement checkedRadio;
    @FindBy(id = "my-radio-2") private WebElement defaultRadio;
    @FindBy (name= "my-colors") private WebElement colorPicker;
    @FindBy (name= "my-date") private WebElement datePicker;
    @FindBy (name= "my-range") private WebElement rangeSlider;
    @FindBy(xpath = "//button[@type='submit']") private WebElement submitButton;
    @FindBy(id = "message") private WebElement successMessage;

    // Constructor
    public WebFormPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String pageTitleText(){
        return pageTitle.getText();
    }

    // Methods for Actions
    public void enterText(String text) {
        sendKeys(textInput, text);
        System.out.println("Entered text: " + text);
    }

    public void enterPassword(String password) {
        sendKeys(passwordInput, password);
        System.out.println("Entered password");
    }

    public void enterTextarea(String text) {
        sendKeys(textareaInput, text);
        System.out.println("Entered text in textarea: " + text);
    }

    public void selectDropdownOption(String value) {
        Select dropdown = new Select(dropdownSelect);
        dropdown.selectByVisibleText(value);
        System.out.println("Selected dropdown option: " + value);
    }

    public void enterDatalistOption(String value) {
        sendKeys(datalistInput, value);
        System.out.println("Entered datalist option: " + value);
    }

    public void uploadFile(String filePath) {
        String newfilePath = Paths.get("src", "test", "resources", filePath).toAbsolutePath().toString();
        fileInput.sendKeys(newfilePath);
        System.out.println("Uploaded file: " + newfilePath);
    }

    public void toggleCheckbox() {
        click(defaultCheckbox);
        System.out.println("Toggled checkbox");
    }

    public void selectRadioButton() {
        click(defaultRadio);
        System.out.println("Selected radio button");
    }

    public void pickColor(String colourHexCode) {
        colorPicker.sendKeys(colourHexCode);
        System.out.println("Changed color to "+ colourHexCode);
    }

    public void pickDate(String date) {
        datePicker.sendKeys(date + Keys.TAB);
        System.out.println("Changed date to "+ date);
    }

    public void setRange(String percentToSet) {
        int percent = Integer.parseInt(percentToSet.replace("%", ""));
        int value = Math.round(10 * (percent / 100f));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1]; arguments[0]" +
                ".dispatchEvent(new Event('input'));", driver.findElement(By.name("my-range")), value);
    }

    public void submitForm() {
        click(submitButton);
        System.out.println("Form submitted");
    }

    // Getter Methods (If Needed)
    public boolean isSubmitButtonDisplayed() {
        return submitButton.isDisplayed();
    }

    public String getConfirmationMessage() {
        return successMessage.getText();
    }
}
