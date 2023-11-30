package coffeeshop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BasePage{

    By fullName = By.id("fullName");
    By partnerTypeDropdown = By.id("partnerType");
    By primeAggregator = By.id("primeAggregator");

    public RegistrationPage(WebDriver webDriver) {
        super(webDriver);
    }

    public RegistrationPage search(String searchItem){
        actions.click(fullName);
        actions.type(fullName, searchItem);
        return this;
    }


    }

