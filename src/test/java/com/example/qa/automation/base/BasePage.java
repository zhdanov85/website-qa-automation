package com.example.qa.automation.base;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public abstract class BasePage {
    protected Page page;
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public BasePage(Page page) {
        this.page = page;
    }

    // Generic navigation method
    public void navigate(String url) {
        logger.info("Navigating to URL: {}", url);
        page.navigate(url);
    }

    // Method to click an element with explicit wait
    public void clickElement(String selector, String elementName) {
        logger.info("Clicking element: {}", elementName);
        Locator locator = page.locator(selector);
        locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(5000));
        locator.click();
    }

    // Method to fill text into an input field with explicit wait
    public void fillElement(String selector, String text, String elementName) {
        logger.info("Filling text \"{}\" into element: {}", text, elementName);
        Locator locator = page.locator(selector);
        locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(5000));
        locator.fill(text);
    }

    // Method to get text from an element
    public String getText(String selector, String elementName) {
        logger.info("Getting text from element: {}", elementName);
        Locator locator = page.locator(selector);
        locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(5000));
        return locator.textContent();
    }

    // Method to check if an element is visible
    public boolean isElementVisible(String selector, String elementName) {
        logger.info("Checking visibility of element: {}", elementName);
        try {
            page.locator(selector).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(5000));
            return true;
        } catch (Exception e) {
            logger.warn("Element {} is not visible: {}", elementName, e.getMessage());
            return false;
        }
    }

    // Method to wait for an element to be visible
    public void waitForElementVisible(String selector, String elementName) {
        logger.info("Waiting for element {} to be visible", elementName);
        page.locator(selector).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(10000));
    }

    // Method to wait for a specific duration
    public void waitFor(long milliseconds) {
        logger.info("Waiting for {} milliseconds", milliseconds);
        page.waitForTimeout(milliseconds);
    }

    // Method to verify page title
    public void verifyPageTitle(String expectedTitle) {
        logger.info("Verifying page title: Expected - \"{}\", Actual - \"{}\"", expectedTitle, page.title());
        assertThat(page).hasTitle(expectedTitle);
    }

    // Method to verify page URL
    public void verifyPageUrl(String expectedUrl) {
        logger.info("Verifying page URL: Expected - \"{}\", Actual - \"{}\"", expectedUrl, page.url());
        assertThat(page).hasURL(expectedUrl);
    }
}
