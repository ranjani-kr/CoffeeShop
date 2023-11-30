package coffeeshop.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    public WebElement waitForElementToBeClickable(WebElement webElement) {
        return wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }
    public WebElement waitForElementToBePresent(By by){
        return wait.until((ExpectedConditions.presenceOfElementLocated(by)));

    }
    public boolean isElementPresent(By locator) {
        try {
            WebElement element = webDriver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public Boolean textToBePresentInElement(By dropdownElement, String visibleText){
        return wait.until((ExpectedConditions.textToBePresentInElement(webDriver.findElement(dropdownElement),visibleText)));

    }

    public WebElement waitForElementToBeClickable(By by) {
        return wait.until((ExpectedConditions.elementToBeClickable(by)));

    }
    public WebElement waitForPresenceOfElementLocated(By by) {
        return wait.until((ExpectedConditions.presenceOfElementLocated(by)));

    }

    public void waitImplicitlyForSomeTime(){
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
    }

    public WebDriver waitForIframeToBeVisible(By by){
        WebElement element = webDriver.findElement(by);
        return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));

    }
}
