package com.example;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import com.example.page_object_model.CardPage;
import com.example.page_object_model.HomePage;
import com.example.page_object_model.LoginPage;
import com.example.page_object_model.ProductCategoryPage;
import com.example.page_object_model.ProductDetaiPage;
import com.example.page_object_model.ShopPage;

/**
 * Unit test for simple App.
 */
public class AppTest {
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
    homePage.open_chrome();
    softAssert = new SoftAssert();
  }

  @Test
  public void extensionOptions() {
    open("https://www.selenium.dev/selenium/web/blank.html");
  }
}
