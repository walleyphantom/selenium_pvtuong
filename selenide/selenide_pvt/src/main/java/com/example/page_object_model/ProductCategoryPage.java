package com.example.page_object_model;

import java.util.Random;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.executeJavaScript;

import com.example.model.Product;
import com.example.utils.Utilities;

import io.qameta.allure.Step;

public class ProductCategoryPage extends GeneralPage {

    // ===== Locators =====
    private final SelenideElement gridViewBtn = $x("//*[contains(@class, 'switch-grid')]");
    private final SelenideElement listViewBtn = $x("//*[contains(@class, 'switch-list')]");
    private final ElementsCollection productList = $$x("//div//h2[@class='product-title']");

    // ===== Methods =====

    @Step("Verify that product list is in Grid View")
    public boolean isGridView() {
        return gridViewBtn.getAttribute("class").contains("switcher-active");
    }

    @Step("Switch product list to List View")
    public ProductCategoryPage switchToListView() {
        listViewBtn.shouldBe(visible).click();
        return this;
    }

    @Step("Switch product list to Grid View")
    public ProductCategoryPage switchToGridView() {
        gridViewBtn.shouldBe(visible).click();
        return this;
    }

    @Step("Click on a random product from the product list")
    public ProductDetaiPage clickRandomProductImg() {
        productList.shouldBe(sizeGreaterThan(0));

        Random rand = new Random();
        int index = rand.nextInt(productList.size());

        SelenideElement randomProduct = productList.get(index);
        SelenideElement priceElement = randomProduct.$x(".//following-sibling::span[@class='price']");
        String name = randomProduct.getText();
        String price = priceElement.getText();

        System.out.println("Selected product: " + name + " | Price: " + price);

        Utilities.product = new Product(name, price, 1);

        executeJavaScript("arguments[0].scrollIntoView({block: 'center'});", randomProduct);
        randomProduct.shouldBe(visible).click();

        return new ProductDetaiPage();
    }
}
