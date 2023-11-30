package coffeeshop.pages;

import coffeeshop.models.Cart;
import coffeeshop.models.Product;
import coffeeshop.models.ShippingMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import coffeeshop.helpers.ShippingRelatedPrices;

public class CartPage extends BasePage{

    By cartItemName = By.cssSelector("a.cart-item__name.h4.break");
    By productSize =
            By.xpath("//div[@class='product-option']/dt[text()='Size: ']/following-sibling::dd");
    By productColor =
            By.xpath("//div[@class='product-option']/dt[text()='Color: ']/following-sibling::dd");
    By productPrice = By.xpath("//td[@class='cart-item__totals right small-hide']/div//span");
    By subTotal =By.className("totals__subtotal-value");
    By cartItems = By.className("cart-item");

    By quantity = By.xpath("//input[@id='Quantity-1']");

    By checkOut =By.id("checkout");
    By percentageDisc = By.xpath("//li[@class='discounts__discount discounts__discount--end']");
    By couponName = By.xpath("");
    private ShippingRelatedPrices extractValue;
    public CartPage(WebDriver webDriver) {

        super(webDriver);
        this.extractValue = new ShippingRelatedPrices(webDriver);
    }

    public Cart getCartDetails() {
        //System.out.println("productPrice"+actions.getChildText(WebElement,productPrice);
        return Cart.builder().
                products(getProductsInTheCart())
                .subTotal(webDriver.findElement(subTotal).getText())
                .coupon(extractText(percentageDisc))
                .couponDiscount(Double.toString(new ShippingRelatedPrices(webDriver).extractAmount(percentageDisc)))
                .total(getTotal())
                .shippingMethod(ShippingMethod.STANDARD)
                .build();
    }
    private List<Product> getProductsInTheCart() {
        List<Product> productsInCart = new ArrayList<>();
        List<WebElement> visibleProductsInTheCart = waits.waitUntilAllElementsAreVisible(cartItems);
        for(WebElement webElement : visibleProductsInTheCart){
            try {
                System.out.println(Product.builder().price(webDriver.findElement(productPrice).getText()));
                Product product = Product.builder().
                    name(actions.getChildText(webElement, cartItemName))
                        .size(actions.getChildText(webElement, productSize))
                        .colour(actions.getChildText(webElement,productColor))
                        .price(webDriver.findElement(productPrice).getText())
                        .build();
                productsInCart.add(product);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return productsInCart;
    }

    public String getTotal(){
        Double subtotal = new ShippingRelatedPrices(webDriver).extractAmount(subTotal);
        System.out.println("SubTotalCartPage :"+subtotal);
        Double discount = new ShippingRelatedPrices(webDriver).extractAmount(percentageDisc);
        System.out.println("DiscountCartPage :"+discount);
//        String subtotal = actions.getText(subTotal);
//        String discount = actions.getText(percentageDisc);
//
//        double subtotalValue = extractNumber(subtotal);
//        double discountValue = extractNumber(discount);
          double total = subtotal + discount;
        System.out.println("Cart Page Manual calculation of SubTotal :"+total);
          return Double.toString(total);
    }
    public static double extractNumber(String input) {
        Pattern pattern = Pattern.compile("(-?\\d+\\.\\d+)"); // Regular expression to match a number
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return Double.parseDouble(matcher.group(1));
        }
        return 0; // Return 0 if no number found
    }
    public String extractText(By by){
        String fullText = webDriver.findElement(by).getText().trim();
        String[] parts = fullText.split("\\s+");
        for (String part : parts) {
            System.out.println(part);
        }
        String result = parts[0];  // This will give you "PERCENTAGE_DISC"
        return result;
    }
    public CheckoutPage checkout() {
        actions.click(checkOut);
        return new CheckoutPage(webDriver);
    }

}
