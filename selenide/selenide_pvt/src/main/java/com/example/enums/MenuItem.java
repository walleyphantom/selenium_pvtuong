package com.example.enums;

import com.example.page_object_model.ShopPage;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MenuItem {
    HOME("Home", null),
    ABOUT_US("About Us", null),
    SHOP("Shop", new ShopPage()),
    OFFERS("Offers", null),
    BLOG("Blog", null),
    CONTACT_US("Contact Us", null);

    private final String label;
    private final Object page;
}