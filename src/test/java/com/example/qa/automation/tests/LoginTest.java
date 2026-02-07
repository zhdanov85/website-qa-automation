package com.example.qa.automation.tests;

import com.example.qa.automation.base.BaseTest;
import com.example.qa.automation.data.TestDataProvider;
import com.example.qa.automation.pages.HomePage;
import com.example.qa.automation.pages.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Feature("Authentication")
@Story("User Login")
public class LoginTest extends BaseTest {

    @Test(dataProvider = "loginData", dataProviderClass = TestDataProvider.class,
            description = "Verify user login functionality")
    @Description("This test attempts to log in a user with provided credentials and verifies the outcome (successful login or error message).")
    @Severity(SeverityLevel.CRITICAL)
    public void testLogin(String email, String password, String expectedUsername, String expectedErrorMessage, boolean isSuccessful) {
        HomePage homePage = new HomePage(getPage());
        LoginPage loginPage = new LoginPage(getPage());

        homePage.navigateToHomePage();
        homePage.clickSignupLogin();
        loginPage.verifyOnLoginPage();

        logger.info("Attempting login with email: {} and password: {}", email, password);
        loginPage.loginUser(email, password);

        if (isSuccessful) {
            logger.info("Verifying successful login for user: {}", expectedUsername);
            loginPage.verifyLoggedInAsUser(expectedUsername);
            assertThat(getPage()).hasURL("https://automationexercise.com/"); // Assuming it redirects to home page
        } else {
            logger.warn("Verifying failed login with error message: {}", expectedErrorMessage);
            loginPage.verifyErrorMessage(expectedErrorMessage);
        }
    }
}
