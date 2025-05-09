package org.framework;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DataManager {

    private static final Properties properties = new Properties();

    static {
        try {
            String propertiesFilePath = System.getProperty("user.dir") + "/resources/data.properties";
            FileInputStream input = new FileInputStream(propertiesFilePath);
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load data.properties file", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    public static String getUrl() {
        return get("url");
    }

    public static String getEmail() {
        return get("email");
    }

    public static String getPassword() {
        return get("password");
    }
}
