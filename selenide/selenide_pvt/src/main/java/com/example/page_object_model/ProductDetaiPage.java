package com.example.page_object_model;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import com.codeborne.selenide.SelenideElement;



public class ProductDetaiPage extends GeneralPage {

    // ===== Element =====
    private final SelenideElement btnAddToCart = $x("//button[@type='submit' and text()='Add to cart']");

    // ===== Action =====
    public void clickAddToCart() {
        btnAddToCart.shouldBe(visible, enabled).click();
    }
}