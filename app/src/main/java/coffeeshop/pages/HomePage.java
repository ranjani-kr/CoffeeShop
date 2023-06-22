package coffeeshop.pages;
import coffeeshop.models.Item;

import coffeeshop.models.Item;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class HomePage {

    WebDriver webDriver;

    By searchIcon =By.className("modal__toggle-open icon icon-search");
    By searchBar = By.id("Search-In-Modal");
    By searchResults = By.cssSelector("li[id^='predictive-search-option'] a");

    By productName = By.cssSelector(".predictive-search__item-heading");
    public HomePage(WebDriver webdriver){
        this.webDriver = webdriver;
    }
    public HomePage search(String searchItem){
        webDriver.findElement(searchIcon).click();
        webDriver.findElement(searchBar).sendKeys(searchItem);
        return this;
    }
    public List<Item>getSearchItems(){
        List<WebElement> elements = webDriver.findElements(searchResults);
        List<Item> items = new ArrayList<>();
        for (WebElement element : elements){
            String name = element.findElement(productName).getText();
            Item item = new Item();
            item.setName(name);
            items.add(item);
        }
        return items;
    }

     public String getSearchResultMessage(){

         return null;
     }

    public List<Item> getSearchResult(){

        return new ArrayList<>();
    }
}
