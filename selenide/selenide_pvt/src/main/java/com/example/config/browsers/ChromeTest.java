package com.example.config.browsers;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;

import com.codeborne.selenide.Configuration;

public class ChromeTest {
    
    public static void apply() {
        Properties props = new Properties();
        try (InputStream input = ChromeTest.class.getClassLoader().getResourceAsStream("chrome-config.properties")) {
            if (input != null) {
                props.load(input);
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not load chrome-config.properties", e);
        }

        ChromeOptions options = new ChromeOptions();

        if (Boolean.parseBoolean(props.getProperty("start-maximized", "false"))) {
            options.addArguments("--start-maximized");
        }

        if (Boolean.parseBoolean(props.getProperty("headless", "false"))) {
            options.addArguments("--headless=new");
        }

        if (Boolean.parseBoolean(props.getProperty("disable-popup-blocking", "false"))) {
            options.addArguments("--disable-popup-blocking");
        }

        if (Boolean.parseBoolean(props.getProperty("disable-extensions", "false"))) {
            options.addArguments("--disable-extensions");
        }

        String extensionPath = props.getProperty("extension-path");
        if (extensionPath != null && !extensionPath.isEmpty()) {
            File extension = new File("src/test/resources/" + extensionPath);
            if (extension.exists()) {
                options.addExtensions(extension);
                options.addArguments("--disable-features=DisableLoadExtensionCommandLineSwitch");
            }
        }

        // ⚙️ Cấu hình cho Selenide từ file properties
        Configuration.browser = props.getProperty("browser", "chrome");

        String timeout = props.getProperty("timeout");
        if (timeout != null) {
            Configuration.timeout = Long.parseLong(timeout);
        }

        String baseUrl = props.getProperty("baseUrl");
        if (baseUrl != null) {
            Configuration.baseUrl = baseUrl;
        }

        Configuration.browser = props.getProperty("browser", "chrome");
        Configuration.browserCapabilities = options;
    }
}