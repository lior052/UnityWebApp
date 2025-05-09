package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HappyFolderMenuPage {

    private final WebDriver driver;

    // locators
    @FindBy(xpath = "//a[@href='/admin/resources/Publisher']//div[normalize-space()='Publisher']")
    private WebElement publisherBtn;

    @FindBy(xpath = "//a[@href='/admin/resources/Post']//div[normalize-space()='Post']")
    private WebElement postBtn;


    // Constructor
    public HappyFolderMenuPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);  // Initializes @FindBy elements
    }

    // Methods
    public PublisherListPage clickPublisher() {
        publisherBtn.click();
        return new PublisherListPage(driver);
    }

    public PostListPage clickPost() {
        postBtn.click();
        return new PostListPage(driver);
    }

}
