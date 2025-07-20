package com.elements;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.common.constants.Constant;
import com.common.constants.Utilities;
import com.model.Product;

public class ProductCategoryPage extends GeneralPage{
     //Locators
    private final By gridView = By.xpath("//*[contains(@class, 'switch-grid')]");
    private final By listView = By.xpath("//*[contains(@class, 'switch-list')]");
    private final By list_product = By.xpath("//div//h2[@class='product-title']");
    private final String subNameRandomProduct = ".//following-sibling::span[@class='price']";

    //a[@class='product-content-image']//ancestor::div[@class='content-product ']//h2[@class='product-title'] 

    //Elements
    protected WebElement getGridView() {return Constant.WEBDRIVER.findElement(gridView);}
    protected WebElement getListView() {return Constant.WEBDRIVER.findElement(listView);}
    protected List<WebElement> getListProduct(){
        return Constant.WEBDRIVER.findElements(list_product);
    }
    
    // Methods
    public boolean isGridView(){
        return getGridView().getAttribute("className").equals("switch-grid switcher-active");
    }

    public ProductCategoryPage switchToListView(){
        getListView().click();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ProductCategoryPage();
    }

    public void switchToGridView(){
        getGridView().click();
    }

    public ProductDetaiPage clickRandomProductImg() {
        List<WebElement> productImgs = getListProduct();
        
        if (productImgs.isEmpty()) {
            System.out.println("No product found!");
            return new ProductDetaiPage();
        }

        Random rand = new Random();
        int randomIndex = rand.nextInt(productImgs.size()-1);
        
        WebElement randomElement = productImgs.get(randomIndex);
        String price = randomElement.findElement(By.xpath(".//following-sibling::span[@class='price']")).getText();
        System.out.println("Selected product price: " + price);
        System.err.println(randomElement.getText()+"Test resutl PVT");
        Utilities.product = new Product(randomElement.getText(),price,1);
        System.out.println(Utilities.product.getName()+"Product name");
       ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].scrollIntoView({block: 'center'});", randomElement);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
        }
        randomElement.click();
        return new ProductDetaiPage();
    }




}
