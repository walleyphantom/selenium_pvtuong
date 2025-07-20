package com.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.common.constants.Constant;
import com.common.constants.Utilities;

public class GeneralPage {
    //Locators
    private final By tabShop = By.xpath("//a[@class='item-link' and contains(text(), 'Shop')]");
    private final By tabCard = By.xpath(" //a[@href = 'https://demo.testarchitect.com/cart/']");
    private final By tabLogin = By.xpath("//span[contains(text(), 'Log in')]");

    private final By tabAllDepartments = By.xpath("//span[text()='All departments']");
    private final String itemProduct ="//a[@class='item-link' and text()='%s']";
    //a[@class='item-link' and normalize-space(text())='shop']

    //Elements
    protected WebElement getTabShop() {return Constant.WEBDRIVER.findElement(tabShop);}
    protected WebElement getTabCard() {return Constant.WEBDRIVER.findElement(tabCard);}
    protected WebElement getTabLogin() { return Constant.WEBDRIVER.findElement(tabLogin);}
    protected WebElement getTabAllDepartments() { return Constant.WEBDRIVER.findElement(tabAllDepartments);}
    public WebElement getItemProduct(String productName) {
        String xpath = String.format(itemProduct, productName);
        return  Constant.WEBDRIVER.findElement(By.xpath(xpath));
    }
    // Methods
    public ShopPage goToShopPage() {
        this.getTabShop().click();
        return new ShopPage();
    }
    public CardPage goToCardPage() {
        this.getTabCard().click();
        return new CardPage();
    }

    public LoginPage goToLoginPage() {
        this.getTabLogin().click();
        return new LoginPage();
    }

    public void hoverTabAllDepartments(){
        Actions actions = new Actions(Constant.WEBDRIVER);
        actions.moveToElement(getTabAllDepartments()).perform(); 
    }

    public ProductCategoryPage goToElectronicSuppliesPage(){
        getItemProduct(Utilities.eletronic_component_supplies).click();
        return new ProductCategoryPage();
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