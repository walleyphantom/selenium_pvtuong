package com.elements;

import java.util.concurrent.TimeUnit;

import com.common.constants.Constant;

public class HomePage extends GeneralPage {
    public HomePage open(){
        Constant.WEBDRIVER.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Constant.WEBDRIVER.navigate().to(Constant.SELE2_URL);
        return this;
    }

}