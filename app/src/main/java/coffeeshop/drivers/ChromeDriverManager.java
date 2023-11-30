package coffeeshop.drivers;
import coffeeshop.drivers.DriverManager;

import coffeeshop.internal.Toggles;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class ChromeDriverManager implements DriverManager<WebDriver>{
    @Override
    public WebDriver create() {
        if(Toggles.HEADLESS.isOn()){
            return createHeadlessChromeDriver();
        }
        return new io.github.bonigarcia.wdm.managers.ChromeDriverManager().create();
    }

    private WebDriver createHeadlessChromeDriver() {
        new io.github.bonigarcia.wdm.managers.ChromeDriverManager().setup();
        ChromeOptions chromeOptions = getHeadlessChromeOptions();
        return new ChromeDriver(chromeOptions);
    }

    private ChromeOptions getHeadlessChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("disable-gpu");
        return chromeOptions;
    }
}

