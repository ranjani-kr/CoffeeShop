package coffeeshop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

        By userEmail = By.id("CustomerEmail");
        By UserPassword = By.id("CustomerPassword");
        By signInButton = By.xpath("//button[contains(text(),'Sign in')]");
        By createAccount = By.linkText("Create account");
        public LoginPage(WebDriver webDriver) {
            super(webDriver);
        }


    public CreateAccountPage navigateToCreateAccountPage() {
        actions.click(createAccount);
        return new CreateAccountPage(webDriver);

    }
}
