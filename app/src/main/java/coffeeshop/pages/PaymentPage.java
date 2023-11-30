package coffeeshop.pages;

import coffeeshop.models.Card;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaymentPage extends BasePage{

    By payNowButton = By.xpath("//span[text() ='Pay now']");
    //By cardNumber = By.xpath("//div[@id='basic-creditCards-collapsible']//span[text()='Card number']");
    By cardNumber = By.xpath("//label[text()='Card number']/following-sibling::input[@data-current-field='number']");
    By nameOnCard = By.xpath("//label[text()='Name on card']/following-sibling::input[@data-current-field='name']");
    By expiry = By.xpath("//label[text()='Expiration date (MM / YY)']/following-sibling::input[@data-current-field='expiry']");
    By expiryYear = By.xpath("//label[text()='Card number']/following-sibling::input[4]");
    By expiryMonth = By.xpath("//label[text()='Card number']/following-sibling::input[3]");
    By securityCode = By.xpath("//input[@data-current-field='verification_value']");
    //By securityCode = By.xpath("//label[text()='Card number']/following-sibling::input[6]");

    By cardIframe = By.xpath("//div[@data-card-field-placeholder='Card number']//iframe[@class='card-fields-iframe']");
    By swithToCardiFrame = By.xpath("//div[@id='number']/iframe[@class='card-fields-iframe']");
    By swithToNameiFrame = By.xpath("//div[@id='name']/iframe[@class='card-fields-iframe']");
    By swithToExpiry = By.xpath("//div[@id='expiry']/iframe[@class='card-fields-iframe']");
    By swithToSecurityCode = By.xpath("//div[@id='verification_value']/iframe[@class='card-fields-iframe']");

    public PaymentPage(WebDriver webDriver) {
        super(webDriver);
    }

    public PaymentPage enterCardDetails(Card cardDetails) {
        actions.scrollToView(cardIframe);
        enterCardDetails(swithToCardiFrame,cardNumber,cardDetails.getNumber());
        enterCardDetails(swithToNameiFrame,nameOnCard,cardDetails.getName());
        enterCardDetails(swithToExpiry,expiry, String.valueOf(cardDetails.getExpiryDate().getMonth()));
        enterCardDetails(swithToExpiry,expiry, String.valueOf(cardDetails.getExpiryDate().getYear()));
        //enterCardDetails(swithToExpiry,expiryYear, String.valueOf(cardDetails.getExpiryDate()));
        enterCardDetails(swithToSecurityCode,securityCode, String.valueOf(cardDetails.getSecurityCode()));

        return this;
    }

    public OrderConfirmationPage payNow(){
        WebElement element = webDriver.findElement(payNowButton);
        JavascriptExecutor executor = (JavascriptExecutor)webDriver;
        executor.executeScript("arguments[0].click();", element);
        //actions.click(continueToPayment);
        return new OrderConfirmationPage(webDriver);
        //actions.click(payNowButton);
        //return new OrderConfirmationPage(webDriver);
    }

    private  PaymentPage enterCardDetails(By iframe,By by,String cardDetails){
        actions.moveToIframe(iframe)
                .type(by,cardDetails)
                .switchtoDefaultFrame();

        return this;
    }
}
