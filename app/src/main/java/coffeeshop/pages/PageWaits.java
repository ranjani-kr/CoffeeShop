package coffeeshop.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;

public class PageWaits {
    private WebDriver webDriver ;
    private FluentWait<WebDriver> wait;
    public PageWaits(WebDriver webDriver){
        this.webDriver = webDriver;
        wait = initWait(webDriver);
    }
    private FluentWait<WebDriver> initWait(WebDriver webDriver){
        return new FluentWait<>(webDriver)
                .withTimeout(Duration.ofSeconds(30)).
                pollingEvery(Duration.ofSeconds(5)).
                ignoring(StaleElementReferenceException.class).
                ignoring(NoSuchElementException.class);
    }

    public List<WebElement> waitUntilAllElementsAreVisible(By by){
        return  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    public WebElement waitForElementToBePresent(By by){
        return wait.until((ExpectedConditions.presenceOfElementLocated(by)));

    }
}
