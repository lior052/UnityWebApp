package org.utils;

import java.util.UUID;

public class TestsDataUtils {

    public static String generateRandomEmail() {
        return "user_" + UUID.randomUUID().toString().substring(0, 8) + "@mail.com";
    }
}
