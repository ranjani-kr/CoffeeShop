package coffeeshop.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageActions {
    private WebDriver webDriver;
    private PageWaits waits;

    public PageActions(WebDriver webDriver){
        this.webDriver = webDriver;
        this.waits = new PageWaits(webDriver);
    }

    public PageActions click(By by) {
        waits.waitForElementToBeClickable(by).click();
        return this;
    }
    public PageActions click(WebElement webElement) {
        waits.waitForElementToBeClickable(webElement).click();
        return this;
    }

    public PageActions type(By by, String value){
        waits.waitForElementToBeClickable(by).sendKeys(value);
        return this;
    }

    public String getChildText(WebElement parent, By by) {
            return parent.findElement(by).getText();
    }
    public PageActions clear(By by) {
        waits.waitForElementToBePresent(by).clear();
        return this;
    }
    public String getText(By by) {
        return waits.waitForElementToBePresent(by).getText();
    }

    public PageActions selectDropDownByVisibleText(By dropdownElement, String state){
        waits.waitForElementToBePresent(dropdownElement);
        Select dropdown = new Select(webDriver.findElement(dropdownElement));
        dropdown.selectByVisibleText(state);
        return this;
    }

    public PageActions typeOnHiddenElement(By by,String value){
        WebElement hiddenElement = webDriver.findElement(by);
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hiddenElement).sendKeys(value).perform();
        return this;
    }

    public PageActions scrollToView(By by){
        WebElement element = webDriver.findElement(by);
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;
        executor.executeScript("arguments[0].scrollIntoView(true);", element);
        return this;
    }

    public PageActions moveToIframe(By by) {

        WebElement frameElement = webDriver.findElement(by);
        try {
            if (waits.isElementPresent(by)) {
                webDriver.switchTo().frame(frameElement);
                System.out.println("Navigated to frame with element " + frameElement);
            } else {
                System.out.println("Unable to navigate to frame with element " + frameElement);
            }
        } catch (NoSuchFrameException e) {
            System.out.println("Unable to locate frame with element " + frameElement + e.getStackTrace());
        } catch (StaleElementReferenceException e) {
            System.out.println("Element with " + frameElement + "is not attached to the page document" + e.getStackTrace());
        } catch (Exception e) {
            System.out.println("Unable to navigate to frame with element " + frameElement + e.getStackTrace());
        }
        return this;
    }

    public void switchtoDefaultFrame() {
        try {
            webDriver.switchTo().defaultContent();
            System.out.println("Navigated back to webpage from frame");
        } catch (Exception e) {
            System.out
                    .println("unable to navigate back to main webpage from frame"
                            + e.getStackTrace());
        }
    }
    public PageActions typeWithJavaScripExecutor(By by, String value){
        //waits.waitForPresenceOfElementLocated(by);
        WebElement hiddenInput = null;
        try {
            hiddenInput = webDriver.findElement(by);
        } catch(NoSuchElementException ex) {
            System.out.println(ex.getMessage());
//            return this;
        }
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].value=arguments[1];", hiddenInput, value);
        return this;
    }
}
