package com.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.common.constants.Constant;

public class ProductDetaiPage extends GeneralPage {
    //Locators
     private final By addToCardBtn = By.xpath("//button[@type='submit' and text()='Add to cart']");
    
    //Elements
    public WebElement getAddToCardBtn() {return Constant.WEBDRIVER.findElement(addToCardBtn);}

    //Methods
    public void clickAddToCardBtn() {
        getAddToCardBtn().click();
    }

 



}
