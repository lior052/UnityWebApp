package org.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class PublisherListPage {


    private final WebDriver driver;
    private final WebDriverWait wait;


    // locators
    @FindBy(css = "a[data-testid='action-new']")
    private WebElement createNewItemBtn;

    @FindBy(css = "a[href='/admin/resources/Publisher']")
    private WebElement publisherList;

    @FindBy(xpath = "//div[contains(text(), 'Successfully created')]")
    private WebElement publisherItemCreatedPopup;


    // Constructor
    public PublisherListPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);  // Initializes @FindBy elements
    }


    // Methods
    public PublisherFormPage createNewPublisherItem() {
        Assert.assertTrue(isPublisherListPage(), "Publisher List page is not displayed");
        createNewItemBtn.click();

        return new PublisherFormPage(driver);
    }

    public boolean isPublisherListPage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(publisherList));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isPublisherItemCreatedPopupDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(publisherItemCreatedPopup));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

}
