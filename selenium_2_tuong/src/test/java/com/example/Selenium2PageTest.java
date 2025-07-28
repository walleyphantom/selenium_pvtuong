package com.example;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.common.constants.Constant;
import com.common.constants.Utilities;
import com.elements.CardPage;
import com.elements.HomePage;
import com.elements.LoginPage;
import com.elements.ProductCategoryPage;
import com.elements.ProductDetaiPage;
import com.elements.ShopPage;
import com.model.Product;

public class Selenium2PageTest {
    HomePage homePage;
    ShopPage shopPage;
    LoginPage loginPage;
    ProductCategoryPage productCategoryPage;
    ProductDetaiPage productDetaiPage;
    CardPage cardPage;
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

    @Test(description = "TC01")
    public void TC01() {
        loginPage = homePage.goToLoginPage();
        loginPage.Login();
        homePage.hoverTabAllDepartments();
        productCategoryPage = homePage.goToElectronicSuppliesPage();
        softAssert.assertTrue(productCategoryPage.isGridView(), "The items do not dispay grid view");
        productCategoryPage = productCategoryPage.switchToListView();
        productDetaiPage = productCategoryPage.clickRandomProductImg();
        productDetaiPage.clickAddToCardBtn();
        cardPage = productDetaiPage.goToCardPage();
        softAssert.assertTrue(cardPage.isCheckIndexRowInfor(Utilities.product, 1),"Row display is not correct");
        cardPage.clickCheckOutBtn();
        softAssert.assertTrue(cardPage.isCheckOutIndexRowInfor(Utilities.product, 1), "Product displays wrong information");
        cardPage.fillBillingDetails(Product.BillingDataFactory.generateBillingDetail());
        cardPage.clickPlaceOrderBtn();
        softAssert.assertTrue(cardPage.isOrderPage(),"Page is not order page");
        System.err.println("Test order results:"+cardPage.isOrderIndexRowInfor(Utilities.product, 1));

    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");
        Constant.WEBDRIVER.quit();
        softAssert.assertAll();
    }
}
