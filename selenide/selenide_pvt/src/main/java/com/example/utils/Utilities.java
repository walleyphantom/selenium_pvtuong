package com.example.utils;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import java.time.Duration;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;
import com.example.model.Product;

public class Utilities {
    public static String user_name = "tuong.phan@agest.vn";
    public static String pass_word = "123456789";

    // Department item
    public static String eletronic_component_supplies = "Electronic Components & Supplies";
    public static Product product;

    public static void closePopupIfExists() {
        executeJavaScript(
                "document.querySelectorAll(\"button.pum-close.popmake-close\").forEach(btn => {" +
                        "  if (btn.offsetParent !== null) {" +
                        "    btn.click();" +
                        "  }" +
                        "});");
    }

    public static void closeCookieNoticeIfExists() {
        executeJavaScript(
                "document.querySelectorAll('#cn-close-notice').forEach(btn => {" +
                        "  if (btn.offsetParent !== null) {" + // chỉ nút visible
                        "    btn.click();" +
                        "  }" +
                        "});");
    }
}
