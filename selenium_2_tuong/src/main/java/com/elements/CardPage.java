package com.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.common.constants.Constant;

public class CardPage extends GeneralPage{
    //Locators
    private final By checkOut = By.xpath("//a[ contains(text(),'Checkout')]");
    private final By placeOrderBtn = By.id("place_order");
    //Elements
    protected WebElement getCheckOut() {return Constant.WEBDRIVER.findElement(checkOut);}
    protected WebElement getPlaceOrderBtn() {return Constant.WEBDRIVER.findElement(placeOrderBtn);}
    //Methods
    public void clickAddToCardBtn() {
        getCheckOut().click();
    }

   //Checkout Page Methods
    public boolean isCheckOutPageURL(){
        return this.checkWebPageURL("https://demo.testarchitect.com/checkout/"); 
    }

    public void clickPlaceOrderBtn(){
        getPlaceOrderBtn().click();
    }

}
