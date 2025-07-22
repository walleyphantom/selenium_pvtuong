package com.example.page_object_model;

import static com.codeborne.selenide.Condition.visible;
import com.codeborne.selenide.ElementsCollection;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import com.codeborne.selenide.SelenideElement;
import com.example.model.BillingDetail;
import com.example.model.Product;


public class CardPage extends GeneralPage {

    // Locators
    private final SelenideElement checkOutBtn = $x("//a[contains(text(),'Checkout')]");
    private final SelenideElement placeOrderBtn = $("#place_order");
    private final SelenideElement titleOrderInfo = $x("//p[text()='Thank you. Your order has been received.']");

    private final ElementsCollection table1Cols = $$x("//div[@class='table-responsive']/table//th");
    private final ElementsCollection orderTableCols = $$x("//*[@class='woocommerce-order-details']/table//th");

    // Billing Fields
    private final SelenideElement firstName = $("#billing_first_name");
    private final SelenideElement lastName = $("#billing_last_name");
    private final SelenideElement address = $("#billing_address_1");
    private final SelenideElement city = $("#billing_city");
    private final SelenideElement zip = $("#billing_postcode");
    private final SelenideElement phone = $("#billing_phone");
    private final SelenideElement email = $("#billing_email");

    // Actions
    public void clickCheckOutBtn() {
        checkOutBtn.click();
    }

    public void clickPlaceOrderBtn() {
        placeOrderBtn.shouldBe(visible).click();
    }

    public boolean isOrderPage() {
        return titleOrderInfo.shouldBe(visible).exists();
    }

    public void fillBillingDetails(BillingDetail billing) {
        firstName.setValue(billing.getFirstName());
        lastName.setValue(billing.getLastName());
        address.setValue(billing.getStreetAddress());
        city.setValue(billing.getCity());
        zip.setValue(billing.getZipCode());
        phone.setValue(billing.getPhone());
        email.clear();
        email.setValue(billing.getEmail());
    }

    public boolean isCheckIndexRowInfor(Product data, int rowIndex) {
        for (int col = 1; col < table1Cols.size(); col++) {
            String header = table1Cols.get(col).text();
            String cellXpath = String.format("//div[@class='table-responsive']/table//tr[%d]/td[%d]", rowIndex, col);
            SelenideElement cell = $x(cellXpath);

            switch (header) {
                case "Price":
                    if (!cell.text().equals(data.getPrice())) return false;
                    break;
                case "Quantity":
                    if (!cell.$("input[type='number']").val().equals(String.valueOf(data.getQuantity())))
                        return false;
                    break;
                case "Product":
                    if (!cell.$(".product-title").text().equals(data.getName())) return false;
                    break;
                default:
                    break;
            }
        }
        return true;
    }

    public boolean isOrderIndexRowInfor(Product data, int rowIndex) {
        for (int col = 1; col < orderTableCols.size(); col++) {
            String header = orderTableCols.get(col).text();
            String cellXpath = String.format("//*[@class='woocommerce-order-details']/table//tr[%d]/td[%d]", rowIndex, col);
            SelenideElement cell = $x(cellXpath);

            switch (header) {
                case "Price":
                    if (!cell.text().equals(data.getPrice())) return false;
                    break;
                case "Product":
                    String[] productSplit = cell.text().split("x");
                    String productName = productSplit[0].replace("\u00A0", " ").replaceAll("\\s+", " ").trim();
                    int quantity = Integer.parseInt(productSplit[1].trim());
                    if (!(productName.equals(data.getName()) && quantity == data.getQuantity())) return false;
                    break;
                default:
                    break;
            }
        }
        return true;
    }
}