package com.example.page_object_model;

import static com.codeborne.selenide.WebDriverRunner.url;

public class ShopPage extends GeneralPage {

    // ===== Method =====
    public boolean isAtUrl(String expectedUrl) {
        return url().equals(expectedUrl);
    }
}