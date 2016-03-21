package com.eprize.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertTrue;

/**
 * Created by dnewell on 3/20/16.
 * This Test Class determines if the site is responding by testing that the page title is present.
 */
public class SiteUp {
    private WebDriver driver;
    private String baseUrl;

    /**
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "https://helloworld.promo.eprize.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    /**
     *
     */
    @Test
    public void TestSite() {
        driver.get("https://helloworld.promo.eprize.com/automationtest/");
        assertTrue(driver.getTitle().startsWith("Sample Solution"));

    }

    /**
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
