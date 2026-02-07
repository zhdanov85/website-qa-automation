package com.example.qa.automation.data;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][]{
                // { email, password, expectedUsername, expectedErrorMessage, isSuccessful }
                { "testuser@example.com", "Test@123", "TestUser", null, true }, // Valid credentials
                { "invalid@example.com", "wrongpassword", null, "Incorrect email or password!", false } // Invalid credentials
        };
    }

    @DataProvider(name = "registrationData")
    public Object[][] getRegistrationData() {
        long timestamp = System.currentTimeMillis();
        return new Object[][]{
                // { name, email }
                { "NewUser" + timestamp, "newuser" + timestamp + "@example.com" }
        };
    }

    // Add more DataProviders as needed for other tests
}
