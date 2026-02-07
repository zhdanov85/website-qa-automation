package com.example.qa.automation.tests;

import com.example.qa.automation.base.BaseTest;
import com.example.qa.automation.pages.HomePage;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test(description = "Verify navigation to home page and title")
    public void shouldNavigateToHomePageAndVerifyTitle() {
        HomePage homePage = new HomePage(getPage());
        homePage.navigateToHomePage();
    }
}
