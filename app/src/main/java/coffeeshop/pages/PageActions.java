package coffeeshop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageActions {
    private WebDriver webDriver;
    private PageWaits waits;

    public PageActions(WebDriver webDriver){
        this.webDriver = webDriver;
        this.waits = new PageWaits(webDriver);
    }

    public void click(By by){
        waits.waitForElementToBePresent(by).click();
    }

    public void type(By by, String value){
        waits.waitForElementToBePresent(by).sendKeys(value);

    }
}
