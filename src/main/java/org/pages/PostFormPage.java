package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class PostFormPage {

    private final WebDriver driver;
    private final WebDriverWait wait;


    // locators
    @FindBy(id = "title")
    private WebElement titleField;

    @FindBy(css = "button[data-testid='someJson-add']")
    private WebElement jsonButton;

    @FindBy(xpath = "//label[text()='Status']/following-sibling::div[contains(@class,'adminjs_Select')]//div[contains(@class,'control')]")
    private WebElement statusDropdown;

    @FindBy(className = "jSSKKG")
    private WebElement publishedCheckedField;

    @FindBy(className = "fULarS")
    private WebElement publishedNotCheckedField;

    @FindBy(xpath = "//label[text()='Publisher']/following-sibling::div[contains(@class,'adminjs_Select')]//div[contains(@class,'control')]")
    private WebElement publisherDropdown;

    @FindBy(css = "button[data-testid='button-save']")
    private WebElement saveBtn;

    @FindBy(css = "a[href='/admin/resources/Post/actions/new']")
    private WebElement postForm;

    @FindBy(css = "a[data-testid='action-edit']")
    private WebElement editButton;


    // Constructor
    public PostFormPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }


    // Methods
    public boolean isPostFormPage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(postForm));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void fillJson() {
        jsonButton.click();
        driver.findElement(By.id("someJson.0.number")).sendKeys("test");
    }

    public void selectStatus(String status) {
        statusDropdown.click();
        WebElement option = driver.findElement(By.xpath("//div[contains(@class,'menu')]//div[text()='" + status + "']"));
        option.click();
    }

    public void checkPublished() {
        if (publishedNotCheckedField.isDisplayed()) {
            publishedNotCheckedField.click();
        }
    }

    public void selectPublisher(String publisherName) {
        publisherDropdown.click();
        WebElement option = driver.findElement(By.xpath(
                "//div[contains(@class,'menu')]//div[text()='" + publisherName + "']"
        ));
        option.click();
    }

    public void fillPostForm(String title, String status, String publisherName) {
        Assert.assertTrue(isPostFormPage(), "Post Form page is not displayed");
        titleField.sendKeys(title);
        fillJson();
        selectStatus(status);
        checkPublished();
        selectPublisher(publisherName);
        saveBtn.click();
    }

    public void updatePostItemStatus(String status) {
        editButton.click();
        selectStatus(status);
        saveBtn.click();
    }
}
