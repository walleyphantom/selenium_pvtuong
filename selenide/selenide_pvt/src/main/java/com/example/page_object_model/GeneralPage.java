package com.example.page_object_model;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.example.enums.Departments;
import com.example.enums.MenuItem;
import com.example.utils.Utilities;

import io.qameta.allure.Step;

public class GeneralPage {

    // Elements
    private final SelenideElement tabShop           = $x("//a[@class='item-link' and contains(text(), 'Shop')]");
    private final SelenideElement tabCart           = $x("//a[@href = 'https://demo.testarchitect.com/cart/']");
    private final SelenideElement tabLogin          = $x("//span[contains(text(), 'Log in')]");
    private final SelenideElement tabAllDepartments = $x("//span[text()='All departments']");

    // Dynamic locator
    private SelenideElement itemProduct(String name) {
        return $x(String.format("//a[@class='item-link' and text()='%s']", name));
    }

    // ===== Navigation Methods =====

    @Step("Go to department page: {department}")
    public Object goToDepartmentItemPage(Departments department) {
        itemProduct(department.getType()).shouldBe(visible).click();
        return department.getPage();
    }

    @Step("Go to department page: {department}")
    public Object goToMenuItemPage(MenuItem department) {
            itemProduct(department.getLabel()).shouldBe(visible).click();
            return department.getPage();
        }


    @Step("Go to Shop Page")
    public ShopPage goToShopPage() {
        tabShop.shouldBe(visible).click();
        return new ShopPage();
    }

    @Step("Go to Cart Page")
    public CardPage goToCartPage() {
        tabCart.shouldBe(visible).click();
        return new CardPage();
    }

    @Step("Go to Login Page")
    public LoginPage goToLoginPage() {
        tabLogin.shouldBe(visible).click();
        closePopupIfVisible();
        return new LoginPage();
    }

    @Step("Go to Electronic Components & Supplies Page")
    public ProductCategoryPage goToElectronicSuppliesPage() {
        itemProduct(Utilities.eletronic_component_supplies).shouldBe(visible).click();
        return new ProductCategoryPage();
    }

    // ===== Actions =====

    @Step("Hover on All Departments")
    public void hoverOnAllDepartments() {
        tabAllDepartments.shouldBe(visible).hover();
    }

    @Step("Check if current URL matches: {expectedUrl}")
    public boolean isCurrentUrl(String expectedUrl) {
        return WebDriverRunner.url().equals(expectedUrl);
    }

    @Step("Close popup if visible")
    public void closePopupIfVisible() {
        SelenideElement popup = $("#pum-5700");
        if (popup.isDisplayed()) {
            popup.$(".pum-close").click();
            popup.should(disappear);
        }
    }
}
