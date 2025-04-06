package org.lexisnexis.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final String CONFIG_FILE = "config.properties";
    private static Properties properties = new Properties();

    static {
        try (InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (inputStream == null) {
                throw new IOException("Unable to find " + CONFIG_FILE + " in the classpath.");
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Error loading configuration file: " + CONFIG_FILE, e);
        }
    }

    public static String getBaseUrl() {
        return properties.getProperty("baseUrl", "https://default-url.com"); // Default if not set
    }

    public static String getBrowser() {
        return properties.getProperty("browser", "edge");
    }

    public static String getDriverPath(String key) {
        return properties.getProperty(key, "");
    }

    public static int getTimeout() {
        return Integer.parseInt(properties.getProperty("timeout", "10"));
    }
}
