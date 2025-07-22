package com.example.page_object_model;

import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import com.codeborne.selenide.SelenideElement;
import com.example.utils.Utilities;

public class LoginPage extends GeneralPage {
    // Elements (Selenide uses SelenideElement, not WebElement)
    private final SelenideElement userNameTextField = $(by("id", "username"));
    private final SelenideElement passWordTextField = $(by("id", "password"));
    private final SelenideElement loginBtn = $(byName("login"));

    // Methods
    public void login(String userName, String password) {
        userNameTextField.setValue(userName);
        passWordTextField.setValue(password);
        loginBtn.click();
    }

    public void login() {
        userNameTextField.setValue(Utilities.user_name);
        passWordTextField.setValue(Utilities.pass_word);
        loginBtn.click();
    }
}