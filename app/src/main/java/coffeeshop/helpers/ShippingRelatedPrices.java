package coffeeshop.helpers;

import coffeeshop.pages.PageActions;
import coffeeshop.pages.PageWaits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static coffeeshop.pages.CartPage.extractNumber;

public class ShippingRelatedPrices  {

    private static WebDriver webDriver;

    public ShippingRelatedPrices(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public static String calculateCartTotal(By subTotal, By discount, By shippingPrice ){
        double total = extractAmount(subTotal)-extractAmount(discount)+extractAmount(shippingPrice);
        String totalValue = Double.toString(total);
        return totalValue;

    }
    public static double extractAmount(By element){
        WebElement locator = webDriver.findElement(element);
        String extractValue = locator.getText();
        System.out.println(extractValue+" is Value @ element:"+element);
        String price = extractValue.replaceAll("[^\\d.]", "");
        double value = extractNumber(price);
        System.out.println("value after converting to double :" +value);
        return value;

    }

}
