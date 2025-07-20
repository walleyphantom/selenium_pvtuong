package com.elements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.common.constants.Constant;

public class ProductCategoryPage extends GeneralPage{
     //Locators
    private final By gridView = By.xpath("//*[contains(@class, 'switch-grid')]");
    private final By listView = By.xpath("//*[contains(@class, 'switch-list')]");
    private final By list_img_product = By.xpath("//a[@class='product-content-image']");

    //Elements
    protected WebElement getGridView() {return Constant.WEBDRIVER.findElement(gridView);}
    protected WebElement getListView() {return Constant.WEBDRIVER.findElement(listView);}
    protected List<WebElement> getListProductImg(){
        return Constant.WEBDRIVER.findElements(list_img_product);
    }
    
    // Methods
    public boolean isGridView(){
        return getGridView().getAttribute("className").equals("switch-grid switcher-active");
    }

    public void switchToListView(){
        getListView().click();
    }

    public void switchToGridView(){
        getGridView().click();
    }


}
