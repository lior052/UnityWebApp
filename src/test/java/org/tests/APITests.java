package org.tests;

import org.api.APIServices;
import org.api.ApiClient;
import org.enums.Status;
import org.framework.DataManager;
import org.pages.HappyFolderMenuPage;
import org.pages.HomePage;
import org.pages.PostListPage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.utils.TestsDataUtils;

/**
 * APITests class contains API tests for the application.
 * It extends the BaseTest class to inherit common test setup and teardown functionality.
 */
public class APITests extends BaseTest {

    ApiClient client;
    APIServices api;
    int publisherId = 0;
    int postId = 0;

    @BeforeTest
    public void init() {
        client = new ApiClient("http://localhost:3000");
        api = new APIServices(client);
        api.login(DataManager.getEmail(), DataManager.getPassword());
    }

    @AfterTest
    public void tearDown() {
        if (postId != 0) api.deletePost(postId);
        if (publisherId != 0) api.deletePublisher(publisherId);

        super.tearDown();
    }

    /**
     * This test method performs the following steps:
     * 1. Logs in to the application using valid credentials.
     * 2. Verifies that the welcome message is displayed.
     * 3. Creates a new publisher item by API request with a random email.
     * 4. Creates a new post item by API request with the publisher's email.
     * 5. Opens the menu and navigates to the Post section.
     * 6. Updates the post status to "Removed" and verifies the update.
     */
    @Test
    public void testAPI() {

        HomePage homePage = loginPage.login(DataManager.getEmail(), DataManager.getPassword());
        // Verify that the welcome message is displayed
        Assert.assertTrue(homePage.isWelcomeMessageDisplayed(), "Welcome message is not displayed");

        String email = TestsDataUtils.generateRandomEmail();
        publisherId = api.createPublisher("Test Publisher", email);

        postId = api.createPost(email, 1, Status.ACTIVE.name(), true, publisherId);

        HappyFolderMenuPage menu = homePage.openMenu();
        PostListPage post = menu.clickPost();
        post.updatePostStatusRemovedAndVerify(email);
    }

}
