package com.example.qa.automation.pages;

import com.example.qa.automation.base.BasePage;
import com.example.qa.automation.config.ConfigReader;
import com.microsoft.playwright.Page;

public class HomePage extends BasePage {
    private final String baseUrl;
    private String signupLoginLink = "a[href=\"/login\"]"; // Assuming this locator for Signup / Login link

    public HomePage(Page page) {
        super(page);
        this.baseUrl = ConfigReader.getInstance().getProperty("base.url");
    }

    public void navigateToHomePage() {
        logger.info("Navigating to Home Page: {}", baseUrl);
        navigate(baseUrl);
        verifyPageTitle("Automation Exercise"); // Verify title after navigation
    }

    public void clickSignupLogin() {
        clickElement(signupLoginLink, "Signup / Login Link");
    }
}
