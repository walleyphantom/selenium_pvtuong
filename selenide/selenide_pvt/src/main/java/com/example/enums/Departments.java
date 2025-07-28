package com.example.enums;

import com.example.page_object_model.ProductCategoryPage;

import lombok.Getter;

@Getter
public enum Departments {
    AUTOMOBILES_MOTORCYCLES("Automobiles & Motorcycles", new ProductCategoryPage()),
    CAR_ELECTRONIC("Car Electronics", new ProductCategoryPage()),
    MOBILE_PHONE_ACCESSORIES("Mobile Phone Accessories", new ProductCategoryPage()),
    COMPUTER_OFFICE("Computer & Office", new ProductCategoryPage()),
    TABLET_ACCESSORIES("Tablet Accessories", new ProductCategoryPage()),
    CONSUMER_ELECTRONICS("Consumer Electronics", new ProductCategoryPage()),
    ELECTRONIC_COMPONENTS_SUPPLIES("Electronic Components & Supplies", new ProductCategoryPage()),
    PHONE_TELECOMMUNICATION("Phones & Telecommunications", new ProductCategoryPage());

    private final String type;
    private final Object page;

    Departments(String type, Object page) {
        this.type = type;
        this.page = page;
    }

    public Object getPage() {
        return page;
    }

    public String getType(){
        return type;
    }
}