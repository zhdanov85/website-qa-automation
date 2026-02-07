package com.example.qa.automation.base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    // Thread-safe Playwright objects
    protected static ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    protected static ThreadLocal<Browser> browser = new ThreadLocal<>();
    protected static ThreadLocal<BrowserContext> context = new ThreadLocal<>();
    protected static ThreadLocal<Page> page = new ThreadLocal<>();

    @BeforeSuite
    public void setupPlaywright() {
        playwright.set(Playwright.create());
        // You can choose browser type here: chromium, firefox, webkit
        browser.set(playwright.get().chromium().launch());
    }

    @AfterSuite
    public void tearDownPlaywright() {
        browser.get().close();
        playwright.get().close();
    }

    @BeforeMethod
    public void setupMethod() {
        context.set(browser.get().newContext());
        page.set(context.get().newPage());
    }

    @AfterMethod
    public void tearDownMethod() {
        context.get().close();
    }

    public Page getPage() {
        return page.get();
    }
}
