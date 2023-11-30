package coffeeshop.pages;

import coffeeshop.models.Cart;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import coffeeshop.helpers.ShippingRelatedPrices;
import org.openqa.selenium.WebElement;

public class ShippingPage extends BasePage {

    By subTotal = By.xpath("//div[span[text()='Subtotal']]/following-sibling::div/span");
    By coupon = By.xpath("//span[text()='Order discount']/parent::*/parent::*/following-sibling::div//span[2]");
    By discount = By.xpath("//span[text()='Order discount']/parent::*/parent::*/following-sibling::div/div[2]/span");
    By shippingCharges = By.xpath("//div[div/div/span[text()='Shipping']]/parent::*/following-sibling::div/span");
    By total = By.xpath("//div[span[text()='Total']]/following-sibling::div//strong");
    By shippingMethod = By.xpath("//fieldset[@id='shipping_methods']/div//p");

    By continueToPayment = By.xpath("//span[text()='Continue to payment']");

    private ShippingRelatedPrices calculatePrice;


    public ShippingPage(WebDriver webDriver) {

        super(webDriver);
        this.calculatePrice = new ShippingRelatedPrices(webDriver);
    }

    public Cart getUpdatedCartDetails() {
        return Cart.builder()
                .subTotal(Double.toString(new ShippingRelatedPrices(webDriver).extractAmount(subTotal)))
                .couponDiscount(Double.toString(new ShippingRelatedPrices(webDriver).extractAmount(discount)))
                .coupon(actions.getText(coupon))
                .shipping(actions.getText(shippingCharges))
                .total(getTotal())
                //.shippingMethod(actions.getText(shippingMethod))
                .build();
    }

    public String getTotal() {
        return  new ShippingRelatedPrices(webDriver).calculateCartTotal(subTotal, discount, shippingCharges);
//        String totalValue = Double.toString(ShippingRelatedPrices.extractAmount(total));
//        if (calculateTotalCart.equals(totalValue)) {
//            return totalValue;
//        }

    }

    public PaymentPage continueToPayment() {
        //waits.waitForElementToBeClickable(continueToPayment).click();
        WebElement element = webDriver.findElement(continueToPayment);
        JavascriptExecutor executor = (JavascriptExecutor)webDriver;
        executor.executeScript("arguments[0].click();", element);
        //actions.click(continueToPayment);
        return new PaymentPage(webDriver);
    }

    public Boolean verifyCartTotal() {
        String calculateTotalCart = new ShippingRelatedPrices(webDriver).calculateCartTotal(subTotal, coupon, shippingCharges);
        String totalValue = Double.toString(ShippingRelatedPrices.extractAmount(total));
        if (calculateTotalCart.equals(totalValue)) {

        }
        return null;
    }
}