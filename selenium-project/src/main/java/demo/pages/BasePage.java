package demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import demo.core.DriverManager;
import java.time.Duration;

/**
 * Base class for all Page Objects with shared utilities like waits and alerts.
 */
public class BasePage {
    protected WebDriver driver;
    protected final Actions actions;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "h1[itemprop='headline']")
    private WebElement currentPageHeader;

    // Returns the text of the shared header element, unique to each page
    public String getCurrentPageHeaderText() {
        return currentPageHeader.getText();
    }

    @FindBy(css = ".attachment-full.size-full")
    private WebElement homepageButton;

    // This method is used to navigate back to the homepage after completing tests for the current page
    public void goToHomePage() {
        waitForElementVisible(homepageButton, 4);
        homepageButton.click();
    }

    // Hovers
    protected void hoverAndClick(WebElement element) {
        actions.moveToElement(element).click().perform();
    }

    protected void hover(WebElement element) {
        actions.moveToElement(element).perform();
    }

    // Alerts
    public String getAlertText() {
        return DriverManager.getDriver().switchTo().alert().getText();
    }

    public void acceptAlert() {
        DriverManager.getDriver().switchTo().alert().accept();
    }

    public void dismissAlert() {
        DriverManager.getDriver().switchTo().alert().dismiss();
    }

    public void sendTextToAlert(String text) {
        DriverManager.getDriver().switchTo().alert().sendKeys(text);
    }

    // Waits
    protected void waitForTextToBe(WebElement element, String expectedText, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        wait.until(ExpectedConditions.textToBePresentInElement(element, expectedText));
    }

    protected void waitForElementVisible(WebElement element, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}