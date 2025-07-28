package com.model;

import com.common.constants.Utilities;
import com.github.javafaker.Faker;

public class Product {
    private String name;
    private String price;
    private int quantity;

    public Product(String name, String price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

  // Getter
    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    // Setter
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public class BillingDataFactory {
    private static final Faker faker = new Faker();

    public static BillingDetail generateBillingDetail() {
        return new BillingDetail(
            faker.name().firstName(),
            faker.name().lastName(),
            faker.address().streetAddress(),
            faker.address().city(),
            "45837",
            faker.phoneNumber().cellPhone(),
            Utilities.user_name
        );
    }
    }
}

