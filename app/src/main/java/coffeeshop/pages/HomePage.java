package coffeeshop.pages;

import coffeeshop.models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage{
    By searchIcon =By.cssSelector("summary[aria-label='Search']");
    By searchBar = By.id("Search-In-Modal");
    By searchResults = By.cssSelector("li[id^='predictive-search-option'] a");
    By searchUnavailableMessage = By.xpath("//p[contains(text(),'No results found for')]");
    By searchIconInSearchBar = By.cssSelector("button[aria-label='Search']");
    By productName = By.cssSelector(".predictive-search__item-heading");



    public HomePage(WebDriver webDriver){
        super(webDriver);
    }
    public HomePage search(String searchItem){
       actions.click(searchIcon);
       actions.type(searchBar, searchItem);
       return this;
    }
    public List<Product> getSearchItems(){
        List<WebElement> elements = waits.waitUntilAllElementsAreVisible(searchResults);
        List<Product> products = new ArrayList<>();
        for (WebElement element : elements){
            String name = actions.getChildText(element, productName);
            Product product =new Product();
            product.setName(name);
            products.add(product);
            System.out.println(name);
        }
        return products;
    }
     public String getSearchResultMessage(){
        WebElement searchResultMessage = waits.waitForElementToBePresent(searchUnavailableMessage);
        String message = searchResultMessage.getText();
        return message;
    }

    public void clickOnSearchIconInSearchBar(){

        actions.click(searchIconInSearchBar);
    }

    public ProductDetailsPage selectProductFromAutoSuggest(String name) {
        WebElement matchingElement = getMatchingElement(name);
        actions.click(matchingElement);
        return new ProductDetailsPage(webDriver);
    }

    private WebElement getMatchingElement(String name) {
        List<WebElement> elements = waits.waitUntilAllElementsAreVisible(searchResults);
        for(WebElement element : elements) {
            String productText = actions.getChildText(element, productName);
            if(productText.equalsIgnoreCase(name)) return element;
        }
        throw new RuntimeException("Cannot find any element with name "+name);
    }

}
