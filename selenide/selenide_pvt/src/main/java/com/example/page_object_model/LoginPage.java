package com.example.page_object_model;

import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import com.example.utils.Utilities;

import io.qameta.allure.Step;

public class LoginPage extends GeneralPage {

    // Elements
    private final SelenideElement userNameTextField = $(by("id", "username"));
    private final SelenideElement passWordTextField = $(by("id", "password"));
    private final SelenideElement loginBtn = $(byName("login"));

    @Step("Login with username: {userName} and password: {password}")
    public void login(String userName, String password) {
        userNameTextField.setValue(userName);
        passWordTextField.setValue(password);
        loginBtn.click();
    }

    @Step("Login with default credentials")
    public void login() {
        userNameTextField.setValue(Utilities.user_name);
        passWordTextField.setValue(Utilities.pass_word);
        loginBtn.click();
    }
}
