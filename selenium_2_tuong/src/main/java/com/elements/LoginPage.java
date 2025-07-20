package com.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.common.constants.Constant;
import com.common.constants.Utilities;

public class LoginPage extends GeneralPage {
    //Locators
    private final By userNameTextField = By.id("username");
    private final By passWordTextField = By.id("password");
    private final By loginBtn = By.xpath("//button[@name='login']");

    //Elements
    protected WebElement getUserNameTextField() {return Constant.WEBDRIVER.findElement(userNameTextField);}
    protected WebElement getPassWordTextField() {return Constant.WEBDRIVER.findElement(passWordTextField);}
    protected WebElement getLoginBtn() {return Constant.WEBDRIVER.findElement(loginBtn);}

    //Methods
    public void Login(String userName, String password){
        getUserNameTextField().sendKeys(userName);
        getPassWordTextField().sendKeys(password);
        getLoginBtn().click();
    }

    public void Login(){
        getUserNameTextField().sendKeys(Utilities.user_name);
        getPassWordTextField().sendKeys(Utilities.pass_word);
        getLoginBtn().click();
    }


}
