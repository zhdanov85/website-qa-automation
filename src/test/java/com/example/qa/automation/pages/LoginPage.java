package com.example.qa.automation.pages;

import com.example.qa.automation.base.BasePage;
import com.microsoft.playwright.Page;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginPage extends BasePage {
    private String signupNameField = "input[data-qa=\"signup-name\"]";
    private String signupEmailField = "input[data-qa=\"signup-email\"]";
    private String signupButton = "button[data-qa=\"signup-button\"]";

    private String loginEmailField = "input[data-qa=\"login-email\"]";
    private String loginPasswordField = "input[data-qa=\"login-password\"]";
    private String loginButton = "button[data-qa=\"login-button\"]";
    private String loggedInAsLocator = "a:has-text(\"Logged in as\")"; // Assuming this locator for successful login
    private String errorMessageLocator = ".login-form p"; // Generic for error messages

    public LoginPage(Page page) {
        super(page);
    }

    public void registerUser(String name, String email) {
        logger.info("Attempting to register user with name: {} and email: {}", name, email);
        fillElement(signupNameField, name, "Signup Name Field");
        fillElement(signupEmailField, email, "Signup Email Field");
        clickElement(signupButton, "Signup Button");
    }

    public void loginUser(String email, String password) {
        logger.info("Attempting to login with email: {}", email);
        fillElement(loginEmailField, email, "Login Email Field");
        fillElement(loginPasswordField, password, "Login Password Field");
        clickElement(loginButton, "Login Button");
    }

    public void verifyLoggedInAsUser(String username) {
        logger.info("Verifying logged in as user: {}", username);
        waitForElementVisible(loggedInAsLocator, "Logged In As User Link");
        assertThat(page.locator(loggedInAsLocator)).containsText(username);
    }

    public void verifyErrorMessage(String expectedError) {
        logger.info("Verifying error message: {}", expectedError);
        waitForElementVisible(errorMessageLocator, "Error Message");
        assertThat(page.locator(errorMessageLocator)).containsText(expectedError);
    }

    public void verifyOnLoginPage() {
        logger.info("Verifying currently on Login Page");
        verifyPageUrl("https://automationexercise.com/login"); // Assuming login page URL
    }
}
