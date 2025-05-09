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

public class PublisherFormPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // locators
    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(css = "button[data-testid='button-save']")
    private WebElement saveBtn;

    @FindBy(css = "a[href='/admin/resources/Publisher/actions/new']")
    private WebElement publisherForm;


    // Constructor
    public PublisherFormPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);  // Initializes @FindBy elements
    }


    // Methods
    public void fillPublisherForm(String name, String email) {
        Assert.assertTrue(isPublisherFormPage(), "Publisher Form page is not displayed");
        nameField.sendKeys(name);
        emailField.sendKeys(email);
        saveBtn.click();
    }

    public boolean isPublisherFormPage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(publisherForm));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
