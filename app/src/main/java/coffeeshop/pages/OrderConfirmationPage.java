package coffeeshop.pages;

import coffeeshop.models.OrderDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderConfirmationPage extends BasePage{

    By orderId = By.xpath("//p[contains(text(),'Confirmation')]");
    By contactInformation = By.xpath("//h3[contains(text(),'Contact information')]/following-sibling::div/p");
    By shippingAddress = By.xpath("//h3[contains(text(),'Shipping address')]/following-sibling::p/address");
    By productSizeandColour = By.xpath("//th[@class='product_description']/span[2]");
    By productName = By.xpath("//th[@class='product__description']/span[1]");


    public OrderConfirmationPage(WebDriver webDriver) {

        super(webDriver);
    }

    public OrderDetails getOrderDetails() {

    return null;

    }
}
