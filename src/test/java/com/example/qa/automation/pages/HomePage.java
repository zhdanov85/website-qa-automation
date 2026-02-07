package com.example.qa.automation.pages;

import com.microsoft.playwright.Page;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class HomePage {
    private Page page;
    private String url = "https://example.com";
    private String headingSelector = "h1";
    private String paragraphSelector = "p";

    public HomePage(Page page) {
        this.page = page;
    }

    public void navigateToHomePage() {
        page.navigate(url);
    }

    public void verifyHeadingText(String expectedText) {
        assertThat(page.locator(headingSelector)).hasText(expectedText);
    }

    public void verifyParagraphText(String expectedText) {
        assertThat(page.locator(paragraphSelector)).hasText(expectedText);
    }
}
