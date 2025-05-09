package org.api;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class ApiClient {

    private final String baseUrl;
    private String sessionCookie;

    public ApiClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setSessionCookie(String sessionCookie) {
        this.sessionCookie = sessionCookie;
    }

    public RequestSpecification request() {
        return RestAssured.given()
                .baseUri(baseUrl)
                .cookie("adminjs", sessionCookie)
                .accept("application/json")
                .log().all();
    }
}
