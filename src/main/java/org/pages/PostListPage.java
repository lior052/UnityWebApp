package org.pages;

import org.enums.Status;
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

public class PostListPage {

    private final WebDriver driver;
    private final WebDriverWait wait;


    // locators
    @FindBy(css = "a[data-testid='action-new']")
    private WebElement createNewItemBtn;

    @FindBy(css = "a[href='/admin/resources/Post']")
    private WebElement postList;

    @FindBy(xpath = "//div[contains(text(), 'Successfully created')]")
    private WebElement postItemCreatedPopup;

    @FindBy(xpath = "//div[contains(text(), 'Successfully updated')]")
    private WebElement postItemUpdatedPopup;


    // Constructor
    public PostListPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);  // Initializes @FindBy elements
    }


    // Methods
    public PostFormPage createNewPostItem() {
        Assert.assertTrue(isPostListPage(), "Post List page is not displayed");
        createNewItemBtn.click();

        return new PostFormPage(driver);
    }

    public boolean isPostListPage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(postList));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isPostItemCreatedPopupDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(postItemCreatedPopup));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isPostItemUpdatedPopupDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(postItemUpdatedPopup));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void openPostItem(String title) {
        driver.findElement(By.xpath(
                "//section[@data-testid='property-list-title' and text()='"+title+"']"
                ))
                .click();
    }

    public void verifyPostStatus(String expectedStatus, String title) {

        WebElement status = driver.findElement(By.xpath(
                "//td[@data-property-name='title' and .//section[text()='" + title + "']]/following-sibling::td[@data-property-name='status']//span"
        ));
        String statusText = status.getText();
        Assert.assertEquals(statusText, expectedStatus, "Post status is not " + expectedStatus);
    }

    public void updatePostStatusRemovedAndVerify(String email) {
        verifyPostStatus(Status.ACTIVE.name(), email);
        openPostItem(email);
        PostFormPage postForm = new PostFormPage(driver);
        postForm.updatePostItemStatus(Status.REMOVED.name());
        Assert.assertTrue(isPostItemUpdatedPopupDisplayed(), "Post item created popup is not displayed");
        verifyPostStatus(Status.REMOVED.name(), email);
    }
}
