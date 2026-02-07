package com.example.qa.automation.pages;

import com.example.qa.automation.base.BasePage;
import com.microsoft.playwright.Page;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CartPage extends BasePage {
    private String cartLink = "a[href=\"/view_cart\"]"; // Assuming this locator for Cart link
    private String proceedToCheckoutButton = ".btn.btn-default.check_out"; // Assuming this locator for checkout button
    private String productNameInCart = ".cart_description a"; // Assuming this locator for product name in cart
    private String emptyCartMessage = "#empty_cart"; // Assuming this for empty cart message

    public CartPage(Page page) {
        super(page);
    }

    public void navigateToCart() {
        logger.info("Navigating to Cart by clicking cart link");
        clickElement(cartLink, "Cart Link");
    }

    public void verifyProductInCart(String productName) {
        logger.info("Verifying product \"{}\" is in cart", productName);
        waitForElementVisible(productNameInCart, "Product Name in Cart");
        assertThat(page.locator(productNameInCart)).hasText(productName);
    }

    public void clickProceedToCheckout() {
        logger.info("Clicking Proceed to Checkout button");
        clickElement(proceedToCheckoutButton, "Proceed to Checkout Button");
    }

    public void verifyCartIsEmpty() {
        logger.info("Verifying if cart is empty");
        assertThat(page.locator(emptyCartMessage)).isVisible();
    }

    public void verifyOnCartPage() {
        logger.info("Verifying currently on Cart Page");
        verifyPageUrl("https://automationexercise.com/view_cart"); // Assuming cart page URL
    }
}
