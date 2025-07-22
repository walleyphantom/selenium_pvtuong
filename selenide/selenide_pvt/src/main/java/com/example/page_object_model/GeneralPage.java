package com.example.page_object_model;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.example.utils.Utilities;

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

    // Navigation Methods
    public ShopPage goToShopPage() {
        tabShop.shouldBe(visible).click();
        return new ShopPage();
    }

    public CardPage goToCartPage() {
        tabCart.shouldBe(visible).click();
        return new CardPage();
    }

    public LoginPage goToLoginPage() {
        tabLogin.shouldBe(visible).click();
        closePopupIfVisible();
        return new LoginPage();
    }

    public ProductCategoryPage goToElectronicSuppliesPage() {
        itemProduct(Utilities.eletronic_component_supplies).shouldBe(visible).click();
        return new ProductCategoryPage();
    }

    // Action Methods
    public void hoverOnAllDepartments() {
        tabAllDepartments.shouldBe(visible).hover();
    }

    public boolean isCurrentUrl(String expectedUrl) {
        return WebDriverRunner.url().equals(expectedUrl);
    }

    public void closePopupIfVisible() {
        SelenideElement popup = $("#pum-5700");
        if (popup.isDisplayed()) {
            popup.$(".pum-close").click(); // hoặc bạn xác định nút close bằng selector phù hợp
            popup.should(disappear); // chờ popup biến mất hoàn toàn
    }
}
}