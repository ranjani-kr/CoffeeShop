package coffeeshop.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;

public class LauncherPage extends BasePage{
    public LauncherPage(WebDriver webDriver){
         super(webDriver);

    }

    public boolean navigateTo(String url){
        webDriver.get(url);
        return isSiteLoaded();

    }

    public boolean isSiteLoaded(){
        return new WebDriverWait(webDriver, Duration.ofSeconds(20)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    }

