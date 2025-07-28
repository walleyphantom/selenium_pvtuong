package com.example;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.example.enums.Departments;
import com.example.model.Product;
import com.example.page_object_model.CardPage;
import com.example.page_object_model.HomePage;
import com.example.page_object_model.LoginPage;
import com.example.page_object_model.ProductCategoryPage;
import com.example.page_object_model.ProductDetaiPage;
import com.example.page_object_model.ShopPage;
import com.example.utils.Utilities;

import io.qameta.allure.Epic;

@Epic("Create 12 Testcase")
public class SelenideOrderTest {
    HomePage homePage;
    ShopPage shopPage;
    LoginPage loginPage;
    ProductCategoryPage productCategoryPage;
    ProductDetaiPage productDetaiPage;
    CardPage cardPage;
    SoftAssert softAssert;

    @BeforeMethod
    public void setUp() {
        System.out.println("Pre-condition");
        homePage = new HomePage();
        homePage.open_defalt();
        softAssert = new SoftAssert();
    }


    @Test(description = "TC01: Verify order placement flow")
    public void TC01() {
        loginPage = homePage.goToLoginPage();
        loginPage.login(); 
        homePage.hoverOnAllDepartments();

        productCategoryPage = homePage.goToElectronicSuppliesPage();
        softAssert.assertTrue(productCategoryPage.isGridView(), "The items do not display in grid view");

        productCategoryPage = productCategoryPage.switchToListView();
        productDetaiPage = productCategoryPage.clickRandomProductImg();

        productDetaiPage.clickAddToCart();
        cardPage = productDetaiPage.goToCartPage();

        softAssert.assertTrue(cardPage.isCheckIndexRowInfor(Utilities.product, 1),"Row display is not correct");

        cardPage.clickCheckOutBtn();
        softAssert.assertTrue(cardPage.isOrderIndexRowInfor(Utilities.product, 1), "Product displays wrong information");

        cardPage.fillBillingDetails(Product.BillingDataFactory.generateBillingDetail());
        cardPage.clickPlaceOrderBtn();

        softAssert.assertTrue(cardPage.isOrderPage(),"Page is not order page");
        System.err.println("Test order results:" + cardPage.isOrderIndexRowInfor(Utilities.product, 1));
    }

    
    @Test(description = "Just test")
    public void TCTest() {
        loginPage = homePage.goToLoginPage();
        loginPage.login(); // dùng hàm login() mới sửa ở LoginPage
        homePage.hoverOnAllDepartments();
        productCategoryPage = (ProductCategoryPage) homePage.goToDepartmentItemPage(Departments.ELECTRONIC_COMPONENTS_SUPPLIES);
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("Post-condition");
        softAssert.assertAll();
    }
}