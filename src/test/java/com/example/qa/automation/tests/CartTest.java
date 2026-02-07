package com.example.qa.automation.tests;

import com.example.qa.automation.base.BaseTest;
import com.example.qa.automation.pages.CartPage;
import com.example.qa.automation.pages.HomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Feature("E-commerce")
@Story("Shopping Cart Functionality")
public class CartTest extends BaseTest {

    // Due to browser inspection limitations, this test will assume direct navigation
    // to a product page and direct adding to cart. In a real scenario, there would
    // be steps to browse products, select one, and then add to cart.
    private final String PRODUCT_PAGE_URL = "https://automationexercise.com/product_details/1"; // Example product URL
    private final String PRODUCT_NAME = "Blue Top"; // Example product name
    private final String ADD_TO_CART_BUTTON = "button.btn.btn-default.cart"; // Assuming this locator
    private final String VIEW_CART_LINK_AFTER_ADD = "a:has-text(\"View Cart\")"; // Assuming this locator

    @Test(description = "Verify adding product to cart and viewing cart")
    @Description("This test navigates to a product page, adds a product to the cart, and verifies it is present in the cart.")
    @Severity(SeverityLevel.NORMAL)
    public void shouldAddProductToCartAndVerify() {
        HomePage homePage = new HomePage(getPage());
        CartPage cartPage = new CartPage(getPage());

        homePage.navigateToHomePage(); // Navigate to home to ensure base URL is set
        homePage.verifyPageTitle("Automation Exercise");

        logger.info("Navigating directly to product page: {}", PRODUCT_PAGE_URL);
        getPage().navigate(PRODUCT_PAGE_URL);

        logger.info("Adding product to cart");
        cartPage.clickElement(ADD_TO_CART_BUTTON, "Add to Cart Button");
        
        // Assuming a popup or success message appears and offers to view cart
        cartPage.clickElement(VIEW_CART_LINK_AFTER_ADD, "View Cart Link after Add");

        cartPage.verifyOnCartPage();
        cartPage.verifyProductInCart(PRODUCT_NAME);
        
        logger.info("Product \"{}\" successfully added to cart and verified.", PRODUCT_NAME);
    }
}
