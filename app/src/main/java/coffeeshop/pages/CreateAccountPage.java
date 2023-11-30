package coffeeshop.pages;

import coffeeshop.models.Customer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateAccountPage extends BasePage{

    By userFirstname = By.id("RegisterForm-FirstName");
    By userLastname = By.id("RegisterForm-LastName");
    By userEmail = By.id("RegisterForm-email");
    By password = By.id("RegisterForm-password");
    By createButton = By.xpath("//button[normalize-space(text())='Create']");
    public CreateAccountPage(WebDriver webDriver) {
        super(webDriver);
    }


    public void createAccount(Customer newCustomer) {
        actions.type(userFirstname, newCustomer.getFirstname())
                .type(userLastname,newCustomer.getLastname())
                .type(userEmail,newCustomer.getEmail())
                .type(password,newCustomer.getPassword())
                .click(createButton);
        waits.waitImplicitlyForSomeTime();

    }
}
