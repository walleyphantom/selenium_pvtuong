package com.example;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.common.constants.Constant;
import com.elements.HomePage;
import com.elements.ShopPage;

class ShopPageTest{
    HomePage homePage;
    ShopPage shopPage;
    SoftAssert softAssert;
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Pre-condition");
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
        homePage = new HomePage();
        homePage.open();
        softAssert =  new SoftAssert();
    }
     @Test(description = "TC01 Verify that the ShopPage URL displays correctlly")
    public void TC01() {
        shopPage = homePage.goToShopPage();
        softAssert.assertTrue(shopPage.isShopPageURL("https://demo.testarchitect.com/shop/"), "The URL for Shop Page displays wrong");
    }
    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");
        Constant.WEBDRIVER.quit();
        softAssert.assertAll();
    }
}