package com.example.qa.automation.base;

import com.example.qa.automation.config.ConfigReader;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTest {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    // Thread-safe Playwright objects
    protected static ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    protected static ThreadLocal<Browser> browser = new ThreadLocal<>();
    protected static ThreadLocal<BrowserContext> context = new ThreadLocal<>();
    protected static ThreadLocal<Page> page = new ThreadLocal<>();

    @BeforeSuite
    public void setupPlaywright() {
        logger.info("Setting up Playwright and launching browser...");
        playwright.set(Playwright.create());
        
        // Example of using ConfigReader for browser type (if configured in env.properties)
        String browserType = ConfigReader.getInstance().getProperty("browser.type", "chromium");
        boolean headless = Boolean.parseBoolean(ConfigReader.getInstance().getProperty("headless", "true"));

        switch (browserType.toLowerCase()) {
            case "firefox":
                browser.set(playwright.get().firefox().launch(new Browser.LaunchOptions().setHeadless(headless)));
                break;
            case "webkit":
                browser.set(playwright.get().webkit().launch(new Browser.LaunchOptions().setHeadless(headless)));
                break;
            case "chromium":
            default:
                browser.set(playwright.get().chromium().launch(new Browser.LaunchOptions().setHeadless(headless)));
                break;
        }
        logger.info("Browser ({}) launched successfully. Headless: {}", browserType, headless);
    }

    @AfterSuite
    public void tearDownPlaywright() {
        logger.info("Tearing down Playwright and closing browser...");
        if (browser.get() != null) {
            browser.get().close();
        }
        if (playwright.get() != null) {
            playwright.get().close();
        }
        logger.info("Playwright and browser closed.");
    }

    @BeforeMethod
    public void setupMethod() {
        logger.info("Setting up new browser context and page for test method...");
        context.set(browser.get().newContext());
        page.set(context.get().newPage());
        logger.info("New context and page created.");
    }

    @AfterMethod
    public void tearDownMethod() {
        logger.info("Tearing down browser context after test method...");
        if (context.get() != null) {
            context.get().close();
        }
        logger.info("Browser context closed.");
    }

    public Page getPage() {
        return page.get();
    }
}
