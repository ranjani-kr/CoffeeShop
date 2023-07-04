package coffeeshop.pages;

import org.openqa.selenium.WebDriver;

public class BasePage {

    protected PageWaits waits;
    protected WebDriver webDriver;
    protected PageActions actions;

    public BasePage(WebDriver webDriver){
        this.webDriver = webDriver;
        this.waits = new PageWaits(webDriver);
        this.actions = new PageActions(webDriver);
    }

}
