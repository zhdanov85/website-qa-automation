package com.example.qa.automation.tests;

import com.example.qa.automation.base.BaseTest;
import com.example.qa.automation.pages.HomePage;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class HomePageTest extends BaseTest {

    @Test
    public void shouldNavigateToHomePageAndVerifyContent() {
        HomePage homePage = new HomePage(getPage());
        homePage.navigateToHomePage();
        assertThat(getPage()).hasTitle("Example Domain");
        homePage.verifyHeadingText("Example Domain");
        homePage.verifyParagraphText("This domain is for use in illustrative examples in documents. You may use this domain in literature without prior coordination or asking for permission.");
    }
}
