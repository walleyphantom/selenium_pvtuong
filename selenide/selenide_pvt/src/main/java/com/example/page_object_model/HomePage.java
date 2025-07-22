package com.example.page_object_model;



import org.openqa.selenium.chrome.ChromeOptions;

import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.Selenide.open;
import com.example.constrant.Constant;

public class HomePage extends GeneralPage {
    public void  open_defalt() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        Configuration.browser = "chrome";
        Configuration.timeout = 8000; // 8 giây thay vì 4
        Configuration.browserCapabilities = options;
        open(Constant.SELE2_URL); // Selenide open(url) 
        // return this;
    }
}