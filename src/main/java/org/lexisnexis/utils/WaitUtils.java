import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class WaitUtils {
    private WebDriver driver;
    private static WebDriverWait wait;

    public WaitUtils(WebDriver driver, int timeout) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    // Wait for element to be visible
    public static WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Wait for element to be clickable
    public static WebElement waitForClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // Wait for element to contain text
    public static boolean waitForText(WebElement element, String expectedText) {
        return wait.until(ExpectedConditions.textToBePresentInElement(element, expectedText));
    }

    // Wait for element attribute to contain value
    public static boolean waitForAttribute(WebElement element, String attribute, String value) {
        return wait.until(ExpectedConditions.attributeContains(element, attribute, value));
    }

    // Wait for element to be selected
    public static boolean waitForSelection(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeSelected(element));
    }
}
