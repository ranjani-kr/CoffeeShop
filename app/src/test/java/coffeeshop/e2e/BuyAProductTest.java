package coffeeshop.e2e;

import coffeeshop.drivers.DriverCreator;
import coffeeshop.models.Cart;
import coffeeshop.models.Customer;
import coffeeshop.models.OrderDetails;
import coffeeshop.models.Product;
import coffeeshop.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class BuyAProductTest {
    @Test(groups = "wip")
    public void verifyThatFirstTimeUserIsAbleToBuyAProduct() throws InterruptedException {

        Customer newCustomer = new Customer().init();
        Product productToBuy = new Product().init();
        WebDriver webDriver =  new DriverCreator().createDriverContext("chrome").create();
        new LauncherPage(webDriver).navigateTo("https://web-playground.ultralesson.com/");

        HomePage homePage = new HomePage(webDriver);
        ProductDetailsPage productDetailsPage = homePage.search(productToBuy.getSearchKeyword())
                                                .selectProductFromAutoSuggest(productToBuy.getName());
        QuickCartSummaryPage quickCartSummaryPage= productDetailsPage.selectSize(productToBuy.getSize())
                                        .selectColour(productToBuy.getColour())
                                        .selectQuantity(productToBuy.getQuantity())
                                        .addToCart();

        Product productAddedToCart = quickCartSummaryPage.verifyProductDetailsInQuickCartSummary();

        assertEquals(productAddedToCart.getName(),productToBuy.getName());
        assertEquals(productAddedToCart.getSize(),productToBuy.getSize());
        assertEquals(productAddedToCart.getColour(),productToBuy.getColour());

        CartPage cartPage = quickCartSummaryPage.viewMyCart();
        Cart cartDetails = cartPage.getCartDetails();
        List<Product> productsInTheCart = cartDetails.getProducts();

        assertEquals(1,productsInTheCart.size());
        Product productInTheCart = productsInTheCart.get(0);
        assertEquals(productInTheCart.getName(),productToBuy.getName());
        assertEquals(productInTheCart.getSize(),productToBuy.getSize());
        assertEquals(productInTheCart.getColour(),productToBuy.getColour());
        //Assert.assertEquals(productInTheCart.getQuantity(),productToBuy.getQuantity());
        Assert.assertEquals(productInTheCart.getPrice(),productToBuy.getPrice());

        cartPage.checkout();
        LoginPage loginPage = new LoginPage(webDriver);
        CreateAccountPage createAccountPage =   loginPage.navigateToCreateAccountPage();
        createAccountPage.createAccount(newCustomer);


        InformationPage informationPage = new InformationPage(webDriver);
        informationPage.fillShippingAddress(newCustomer.getShippingAddress());
        Customer customerContactInformation = informationPage.getContactInformation();
        Cart cartDetailsInInformationPage = informationPage.getCartDetails();


        assertEquals(customerContactInformation.getFirstname(), newCustomer.getFirstname());
        assertEquals(customerContactInformation.getLastname(), newCustomer.getLastname());
        assertEquals(customerContactInformation.getEmail(), newCustomer.getEmail());

        Assert.assertEquals(cartDetailsInInformationPage.getSubTotal(), cartDetails.getTotal());
        Assert.assertEquals(cartDetailsInInformationPage.getCoupon(), cartDetails.getCoupon());
        Assert.assertEquals(cartDetailsInInformationPage.getCouponDiscount(),cartDetails.getCouponDiscount());
        Assert.assertEquals(cartDetailsInInformationPage.getTotal(), "310.24");


        ShippingPage shippingPage = informationPage.continueToShipping();
        Cart cartDetailsInShippingPage = shippingPage.getUpdatedCartDetails();

        //Assert.assertEquals(cartDetailsInShippingPage.getTotal(), "310.24");
        Assert.assertEquals(cartDetailsInShippingPage.getSubTotal(), cartDetailsInInformationPage.getSubTotal());
        Assert.assertEquals(cartDetailsInShippingPage.getCoupon(),cartDetailsInInformationPage.getCoupon());
        Assert.assertEquals(cartDetailsInShippingPage.getCouponDiscount(), cartDetailsInInformationPage.getCouponDiscount());

//        PaymentPage paymentPage = shippingPage.continueToPayment();
//        OrderConfirmationPage orderConfirmationPage = paymentPage
//                .enterCardDetails(newCustomer.getCardDetails())
//                .payNow();
//       // OrderDetails orderDetails = orderConfirmationPage.getOrderDetails();
//
//
////        Assert.assertFalse(orderDetails.getOrderId().isEmpty());
////        Assert.assertEquals(orderDetails.getCustomerDetails(), newCustomer);
////        Assert.assertEquals(orderDetails.getCartSummary(), cartDetailsInShippingPage);

    }
}
