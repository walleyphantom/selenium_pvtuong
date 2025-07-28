package com.example.page_object_model;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeSuite;

import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.Selenide.open;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.example.config.browsers.ChromeTest;
import com.example.constrant.Constant;

import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;

public class HomePage extends GeneralPage {
    @BeforeSuite
    public void setUpAllureSelenideListener() {
        SelenideLogger.addListener("AllureSelenide",
            new AllureSelenide()
                .screenshots(true)      // Tự động chụp ảnh khi fail
                .savePageSource(false)  // Không lưu page source
        );
    }




    @Step("Open browser with default settings and navigate to '{Constant.SELE2_URL}'")
    public void open_defalt() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        Configuration.browser = "chrome";
        Configuration.timeout = 8000;
        Configuration.browserCapabilities = options;

        open(Constant.SELE2_URL);
    }

    @Step("Open browser using ChromeTest configuration")
    public void open_chrome() {
        ChromeTest.apply(); // Apply custom Chrome settings
    }
}
