package coffeeshop.pages;

import coffeeshop.helpers.ShippingRelatedPrices;
import coffeeshop.models.Address;
import coffeeshop.models.Cart;
import coffeeshop.models.Customer;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InformationPage extends BasePage {
    private By contactDetails = By.xpath("//h2[contains(text(), 'Contact')]/following-sibling::div//span");

    By lastname = By.name("lastName");
   // By contactDetails = By.className("_19gi7yt0 _19gi7ytg _1fragem1q");

    By selectCountryDropdown = By.name("countryCode");
    By address = By.id("address1");
    By apartment = By.name("address2");
    By city = By.name("city");
    By selectStateDropdown = By.name("zone");
    By postCode = By.name("postalCode");
    By continueToShipping = By.xpath("//button[span[text()='Continue to shipping']]");

    By subtotal = By.xpath("//div[span[text()='Subtotal']]/following-sibling::div/span");
    By coupon = By.xpath("//div[h3[text()='Cost summary']]/following-sibling::div//span[2]");
    By discount = By.xpath("//span[text()='Order discount']/parent::*/parent::*/following-sibling::div/div[2]/span");

        By shipping = By.xpath("//div[div/div/span[text()='Shipping']]/following-sibling::div/span");
    By total = By.xpath("//div[span[text()='Total']]/following-sibling::div//strong");
    private ShippingRelatedPrices extractValue ;

    public InformationPage(WebDriver webDriver) {

        super(webDriver);
        this.extractValue = new ShippingRelatedPrices(webDriver);
    }

    public Cart getCartDetails() {
        System.out.println("SubTotal"+Cart.builder().subTotal(actions.getText(subtotal)));
        return Cart.builder()
                .subTotal(extractAmount(subtotal))
                .coupon(actions.getText(coupon))
                .couponDiscount(Double.toString(new ShippingRelatedPrices(webDriver).extractAmount(discount)))
                //.shipping(actions.getText(shipping))
                .total(extractAmount(total))
                .build();


    }

    public String extractAmount(By element){
        String price = Double.toString(new ShippingRelatedPrices(webDriver).extractAmount(element));
        System.out.println("SubTotal InformationPage :" + price);
//        String totalValue = webDriver.findElement(element).getText();
//        System.out.println(totalValue+"totalValue");
//        String stringTotal = totalValue.replaceAll("[^\\d.]", "");
//        return stringTotal;
        return price;

    }

    public  InformationPage fillShippingAddress(Address shippingAddress) {
        actions.selectDropDownByVisibleText(selectCountryDropdown, shippingAddress.getCountry())
                .type(address, shippingAddress.getAddress())
                .type(apartment, shippingAddress.getApartment())
                .type(city, shippingAddress.getCity())
                .selectDropDownByVisibleText(selectStateDropdown, shippingAddress.getState())
                .type(postCode, shippingAddress.getPostcode());
                return this;
    }

    public ShippingPage continueToShipping() {
        actions.click(continueToShipping);
        return new ShippingPage(webDriver);

    }

    public Customer getContactInformation() {
        WebElement customerInfo = webDriver.findElement(contactDetails);
        Customer customerProfile = new Customer();
        String fullText = customerInfo.getText();
        Pattern pattern = Pattern.compile("(.+?) (.+?) \\((.+?)\\)");
        Matcher matcher = pattern.matcher(fullText);

        if (matcher.find()) {
            String firstname = matcher.group(1);
            String lastname = matcher.group(2);
            String email = matcher.group(3);
            customerProfile.setFirstname(firstname);
            customerProfile.setLastname(lastname);
            customerProfile.setEmail(email);
        }
        return customerProfile;

    }

}
