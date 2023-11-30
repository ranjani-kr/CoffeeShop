package coffeeshop;

import coffeeshop.drivers.DriverCreator;
import coffeeshop.models.Item;
import coffeeshop.models.Product;
import coffeeshop.pages.HomePage;
import coffeeshop.pages.LauncherPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;

import java.util.List;


public class SearchTests {
    @Test
    public void verifyIfSearchTermShowsRelevantResults() {
        //Arrange
        String searchItem = "Jeans";
        String searchKey = "Jean";
        String browser = "chrome";
        WebDriver webDriver = new DriverCreator().createDriverContext(browser).create();

        LauncherPage launcherPage = new LauncherPage(webDriver); // Assume webdriver is created and                                                                      // handy
        launcherPage.navigateTo("https://web-playground.ultralesson.com/");

        //Act
        HomePage homepage = new HomePage(webDriver);
        homepage.search(searchItem);
        List<Product> searchProducts = homepage.getSearchItems();

        //Assert
        Assert.assertEquals( searchProducts.size(),4);
        Assert.assertTrue(searchProducts.stream().allMatch(product-> product.getName().contains(searchKey)));
        }

    @Test
    public void verifySearchResultForUnavailableProduct(){

        //Arrange
        String searchUnavailableItem = "Tennis Racquet";
        String browser = "chrome";
        WebDriver webDriver = new DriverCreator().createDriverContext(browser).create();
        LauncherPage launcherPage = new LauncherPage(webDriver);
        launcherPage.navigateTo("https://web-playground.ultralesson.com/");

        //Act
        HomePage homepage = new HomePage(webDriver);
        homepage.search(searchUnavailableItem);
        homepage.clickOnSearchIconInSearchBar();
        String searchUnavailableMessage = homepage.getSearchResultMessage();

        //Assert
        Assert.assertEquals(searchUnavailableMessage,"No results found for “Tennis Racquet”. Check the spelling or use a different word or phrase.");
        }

    }
