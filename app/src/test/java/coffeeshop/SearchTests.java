package coffeeshop;

import coffeeshop.models.Item;
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
        WebDriver webDriver = null;
        LauncherPage launcherPage = new LauncherPage(webDriver); // Assume webdriver is created and                                                                      // handy
        launcherPage.navigateTo("https://web-playground.ultralesson.com/");

        //Act
        HomePage homepage = new HomePage(webDriver);
        homepage.search(searchItem);
        List<Item> searchItems = homepage.getSearchItems();

        //Assert
        Assert.assertEquals(4, searchItems.size());
        Assert.assertTrue(searchItems.stream().allMatch(item -> item.getName().contains(searchKey)));
        }
    }
