package com.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.common.constants.Constant;

public class GeneralPage {
    //Locators
    private final By tabShop = By.xpath("//a[@class='item-link' and contains(text(), 'Shop')]");
    private final By tabCard = By.xpath(" //a[@href = 'https://demo.testarchitect.com/cart/']");

    //a[@class='item-link' and normalize-space(text())='shop']

    //Elements
    protected WebElement getTabShop() {return Constant.WEBDRIVER.findElement(tabShop);}
    protected WebElement getTabCard() {return Constant.WEBDRIVER.findElement(tabCard);}
    // Methods
    public ShopPage goToShopPage() {
        this.getTabShop().click();
        return new ShopPage();
    }
    public CardPage goToCardPage() {
        this.getTabCard().click();
        return new CardPage();
    }



    public boolean checkWebPageURL(String url){
        // try {
        //     Thread.sleep(5000);
        // } catch (InterruptedException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
        return Constant.WEBDRIVER.getCurrentUrl().equals(url);
    }





}