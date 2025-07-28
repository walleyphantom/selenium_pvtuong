package com.example.page_object_model;

import static com.codeborne.selenide.WebDriverRunner.url;

import io.qameta.allure.Step;

public class ShopPage extends GeneralPage {

    // ===== Method =====
    @Step("Verify the current URL is '{expectedUrl}'")
    public boolean isAtUrl(String expectedUrl) {
        return url().equals(expectedUrl);
    }
}
