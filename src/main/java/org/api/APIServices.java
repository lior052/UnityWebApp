package org.api;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class APIServices {

    private final ApiClient apiClient;

    public APIServices(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public void login(String email, String password) {
        Response response = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("email", email)
                .formParam("password", password)
                .post("http://localhost:3000/admin/login");

        String cookie = response.getCookie("adminjs");

        if (cookie == null || cookie.isEmpty()) {
            throw new RuntimeException("Login failed - session cookie missing.");
        }

        apiClient.setSessionCookie(cookie);
    }

    public int createPublisher(String name, String email) {
        Response response = apiClient.request()
                .multiPart("name", name)
                .multiPart("email", email)
                .post("/admin/api/resources/Publisher/actions/new");

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to create publisher. Status: " + response.getStatusCode());
        }

        return response.jsonPath().getInt("record.params.id");
    }

    public void deletePublisher(int publisherId) {
        Response response = apiClient.request()
                .post("/admin/api/resources/Publisher/records/" + publisherId + "/delete");

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to delete publisher. Status: " + response.getStatusCode());
        }
    }

    public int createPost(String title, int number, String status, boolean published, int publisherId) {
        Response response = apiClient.request()
                .multiPart("title", title)
                .multiPart("someJson.0.number", number)
                .multiPart("status", status)
                .multiPart("published", published)
                .multiPart("publisher", publisherId)
                .post("/admin/api/resources/Post/actions/new");

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to create post. Status: " + response.getStatusCode());
        }

        return response.jsonPath().getInt("record.params.id");
    }

    public void deletePost(int postId) {
        Response response = apiClient.request()
                .post("/admin/api/resources/Post/records/" + postId + "/delete");

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to delete post. Status: " + response.getStatusCode());
        }
    }

    public void deletePostAndPublisher(int postId, int publisherId) {
        deletePost(postId);
        deletePublisher(publisherId);
    }
}
