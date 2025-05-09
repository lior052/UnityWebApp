package org.tests;

import org.enums.Status;
import org.framework.DataManager;
import org.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.utils.TestsDataUtils;

/**
 * UITests class contains UI tests for the application.
 * It extends the BaseTest class to inherit common test setup and teardown functionality.
 */
public class UITests extends BaseTest {


    /**
     * This test method performs the following steps:
     * 1. Logs in to the application using valid credentials.
     * 2. Verifies that the welcome message is displayed.
     * 3. Opens the menu and navigates to the Publisher section.
     * 4. Creates a new publisher item with a random email.
     * 5. Navigates to the Post section and creates a new post item with the publisher's email.
     * 6. Updates the post status to "Removed" and verifies the update.
     */
    @Test
    public void testUI() {

        HomePage homePage = loginPage.login(DataManager.getEmail(), DataManager.getPassword());
        // Verify that the welcome message is displayed
        Assert.assertTrue(homePage.isWelcomeMessageDisplayed(), "Welcome message is not displayed");

        HappyFolderMenuPage menu = homePage.openMenu();

        //publisher steps
        PublisherListPage publisher = menu.clickPublisher();
        PublisherFormPage publisherForm = publisher.createNewPublisherItem();

        String email = TestsDataUtils.generateRandomEmail();
        publisherForm.fillPublisherForm("Test", email);
        Assert.assertTrue(publisher.isPublisherItemCreatedPopupDisplayed(), "Publisher item created popup is not displayed");

        //post steps
        PostListPage post = menu.clickPost();
        PostFormPage postForm = post.createNewPostItem();
        postForm.fillPostForm(email, Status.ACTIVE.name(), email);
        Assert.assertTrue(post.isPostItemCreatedPopupDisplayed(), "Post item created popup is not displayed");

        //update post status and validate
        post.updatePostStatusRemovedAndVerify(email);

    }

}
