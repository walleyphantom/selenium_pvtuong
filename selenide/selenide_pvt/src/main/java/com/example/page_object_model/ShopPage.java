package com.example.page_object_model;

import static com.codeborne.selenide.WebDriverRunner.url;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.example.model.Product;
import com.example.utils.Utilities;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import lombok.Getter;

public class ShopPage extends GeneralPage {
    // Variable to store the selected product
    @Getter
    private final List<Product> selectedProducts = new ArrayList<>();
    // ===== Locators =====
    private final ElementsCollection productContainers = $$x("//div[@class='content-product ']");
    private final String titleXpath = ".//h2[@class='product-title']";
    private final String priceXpath = ".//*[@class='price']";
    private final String addToCartXpath = ".//a[text()='Add to cart']";

    // ===== Method =====
    @Step("Verify the current URL is '{expectedUrl}'")
    public boolean isAtUrl(String expectedUrl) {
        return url().equals(expectedUrl);
    }

    @Step("Click 'Add to cart' on a random product (default 1 time)")
    public void clickRandomProductAddToCart() {
        clickRandomProductAddToCart(1);
    }

    @Step("Click 'Add to cart' on a random product with random times")
    public void clickRandomProductAddToCartRandomTimes() {
        int randomTimes = new Random().nextInt(5) + 1;
        clickRandomProductAddToCart(randomTimes);
    }

    @Step("Click 'Add to cart' on a random product {times} time(s)")
    public void clickRandomProductAddToCart(int times) {
        productContainers.shouldBe(sizeGreaterThan(0));

        Random rand = new Random();
        for (int i = 0; i < times; i++) {
            SelenideElement randomProduct = productContainers.get(rand.nextInt(productContainers.size()));

            String name = randomProduct.$x(titleXpath).getText().trim();
            String price = randomProduct.$x(priceXpath).getText().trim();

            executeJavaScript("arguments[0].scrollIntoView({block: 'center'});", randomProduct);

            randomProduct.$x(addToCartXpath).shouldBe(visible).click();

            selectedProducts.add(new Product(name, price, 1));
        }
    }

    public static BigDecimal calculateTotal(List<Product> products) {
        return products.stream()
                .map(p -> {
                    String cleanedPrice = p.getPrice()
                            .replace("$", "")
                            .replace(",", "")
                            .trim();
                    BigDecimal priceValue = new BigDecimal(cleanedPrice);
                    return priceValue.multiply(BigDecimal.valueOf(p.getQuantity()));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Step("Get all selected products")
    public List<Product> getSelectedProducts() {
        return selectedProducts;
    }
}
