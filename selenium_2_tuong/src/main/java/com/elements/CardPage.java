package com.elements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.common.constants.Constant;
import com.model.BillingDetail;
import com.model.Product;

public class CardPage extends GeneralPage{
    //Locators
    private final By checkOut = By.xpath("//a[contains(text(),'Checkout')]");
    private final By placeOrderBtn = By.id("place_order");
    private final By _table1Column = By.xpath("//div[@class='table-responsive']/table//th");
    private final By _table1Row = By.xpath("//div[@class='table-responsive']/table//tr");
    private final String _table1RowColumn = "//div[@class='table-responsive']/table//tr[%d]//td[%d]";
    private final By checkouTableColumn = By.xpath("//div[@id='order_review']/table//th");
    private final By checkouTableRow = By.xpath("//div[@id='order_review']/table//tr");
    private final String cneckOuttableRowColumn = "//div[@id='order_review']/table//tr[%d]//td[%d]";
    private final By orderTableColumn = By.xpath("//*[@class='woocommerce-order-details']/table//th");
    private final By orderTableRow = By.xpath("//*[@class='woocommerce-order-details']/table//tr");
    private final String orderTableRowColumn = "//*[@class='woocommerce-order-details']/table//tr[%d]//td[%d]";
    private final By titleOrderinfor = By.xpath("//p[text()='Thank you. Your order has been received.']");

    private final By firstName = By.id("billing_first_name");
    private final By lastName = By.id("billing_last_name");
    private final By address = By.id("billing_address_1");
    private final By town_or_city = By.id("billing_city");
    private final By zipCode = By.id("billing_postcode");
    private final By phone = By.id("billing_phone");
    private final By email = By.id("billing_email");


    //Elements
    protected WebElement getCheckOut() {return Constant.WEBDRIVER.findElement(checkOut);}
    protected WebElement getTitleOrderinfor() {return Constant.WEBDRIVER.findElement(titleOrderinfor);}
    protected WebElement getPlaceOrderBtn() {return Constant.WEBDRIVER.findElement(placeOrderBtn);}
    protected List<WebElement> getTable1Column() {return Constant.WEBDRIVER.findElements(_table1Column);}
    protected List<WebElement> getTable1Row() {return Constant.WEBDRIVER.findElements(_table1Row);}
    protected WebElement getTable1RowColumn(int indexRow, int indexColumn) {
        return Constant.WEBDRIVER.findElement(By.xpath(String.format(_table1RowColumn, indexRow, indexColumn)));
    }

        protected WebElement getFirstNameField() {
        return Constant.WEBDRIVER.findElement(firstName);
    }

    protected WebElement getLastNameField() {
        return Constant.WEBDRIVER.findElement(lastName);
    }

    protected WebElement getAddressField() {
        return Constant.WEBDRIVER.findElement(address);
    }

    protected WebElement getTownOrCityField() {
        return Constant.WEBDRIVER.findElement(town_or_city);
    }

    protected WebElement getZipCodeField() {
        return Constant.WEBDRIVER.findElement(zipCode);
    }

    protected WebElement getPhoneField() {
        return Constant.WEBDRIVER.findElement(phone);
    }

    protected WebElement getEmailField() {
        return Constant.WEBDRIVER.findElement(email);
    }

    protected List<WebElement> getOrderTableRows() {
        return Constant.WEBDRIVER.findElements(orderTableRow);
    }

    protected List<WebElement> getOrderTableColumns() {
        return Constant.WEBDRIVER.findElements(orderTableColumn);
    }

    protected WebElement getOrderTableCell(int rowIndex, int columnIndex) {
        return Constant.WEBDRIVER.findElement(By.xpath(String.format(orderTableRowColumn, rowIndex, columnIndex)));
    }

    //Methods
    public void clickAddToCardBtn() {
        getCheckOut().click();
    }
    //Shoping cart tab
    public boolean isCheckIndexRowInfor(Product data, int index){
            List<WebElement> cols = this.getTable1Column();
            for (int j = 1; j<cols.size(); j++) {
                System.out.println(getTable1RowColumn(index,j).getText());
              
                switch (cols.get(j).getText()) {
                    case "Price":
                        if(!getTable1RowColumn(index,j)
                        .getText().equals(data.getPrice())) return false;
                        break;
                    case "Quantity":
        
                        if(!getTable1RowColumn(index,j).findElement(By.xpath(".//input[@type='number']"))
                        .getText().equals(data.getQuantity())) return false;
                        break;
                    case "Product":
                        if(!getTable1RowColumn(index,j).findElement(By.xpath(".//a[@class='product-title']"))
                        .getText().equals(data.getName())) return false;
                        break;
                    default:
                        break;
                }
            }
            return true;
        }



    //Checkout Page Methods
    public boolean isCheckOutPageURL(){
        return this.checkWebPageURL("https://demo.testarchitect.com/checkout/"); 
    }

    public boolean isOrderPage(){
        return getTitleOrderinfor().isDisplayed(); 
    }

    public boolean isCheckOutIndexRowInfor(Product data, int index){
            List<WebElement> cols = this.getTable1Column();
            for (int j = 1; j<cols.size(); j++) {
                System.out.println(getTable1RowColumn(index,j).getText());
              
                switch (cols.get(j).getText()) {
                    case "Price":
                        if(!getTable1RowColumn(index,j)
                        .getText().equals(data.getPrice())) return false;
                        break;
                    case "Product":
                        String fullText = getTable1RowColumn(index,j).getText();          
                        String[] productOnly = fullText.split("x");
                        String normalized = productOnly[0].replace("\u00A0", " ") // thay &nbsp;
                               .replaceAll("\\s+", " ") // gộp tất cả whitespace thành 1 dấu cách
                               .trim();
                        if(!(normalized.equals(data.getName()) && Integer.parseInt(productOnly[1])==(data.getQuantity()))) return false;
                        break;

                    default:
                        break;
                }
            }
            return true;
        }
    
    public void fillBillingDetails(BillingDetail billing) {
        getFirstNameField().sendKeys(billing.getFirstName());
        getLastNameField().sendKeys(billing.getLastName());
        getAddressField().sendKeys(billing.getStreetAddress());
        getTownOrCityField().sendKeys(billing.getCity());
        getZipCodeField().sendKeys(billing.getZipCode());
        getPhoneField().sendKeys(billing.getPhone());
        getEmailField().clear();
        getEmailField().sendKeys(billing.getEmail());
    }
    
    //Order Page    
    public boolean isOrderIndexRowInfor(Product data, int index){
            List<WebElement> cols = this.getOrderTableColumns();
            for (int j = 1; j<cols.size(); j++) {
                System.out.println(getTable1RowColumn(index,j).getText());
              
                switch (cols.get(j).getText()) {
                    case "Price":
                        if(!getOrderTableCell(index,j)
                        .getText().equals(data.getPrice())) return false;
                        break;
                    case "Product":
                        String fullText = getOrderTableCell(index,j).getText();          
                        String[] productOnly = fullText.split("x");
                        String normalized = productOnly[0].replace("\u00A0", " ") // thay &nbsp;
                               .replaceAll("\\s+", " ") // gộp tất cả whitespace thành 1 dấu cách
                               .trim();
                        if(!(normalized.equals(data.getName()) && Integer.parseInt(productOnly[1])==(data.getQuantity()))) return false;
                        break;

                    default:
                        break;
                }
            }
            return true;
        }


    public void clickCheckOutBtn(){
        getCheckOut().click();
    }
    public void clickPlaceOrderBtn(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
        }

        getPlaceOrderBtn().click();
    }

}
