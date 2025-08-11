package com.example.page_object_model;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;

import io.qameta.allure.Step;

public class ProductDetaiPage extends GeneralPage {

    // ===== Element =====
    private final SelenideElement btnAddToCart = $x("//button[@type='submit' and text()='Add to cart']");

    // ===== Action =====

    @Step("Click 'Add to cart' button on product detail page")
    public void clickAddToCart() {
        btnAddToCart.shouldBe(visible, enabled).click();
    }
}
