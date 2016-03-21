package com.eprize.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class FailLogin {
    private WebDriver driver;
    private String baseUrl;
    private String userEmailFail = "newell.donald@gmail.con";
    private String failText = "Enter Now";

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "https://helloworld.promo.eprize.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testLoginValid() throws Exception {
        driver.get(baseUrl + "automationtest/");
        try {
            driver.findElement(By.linkText("Returning?")).click();  //make sure the correct page loads and this element is present
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(userEmailFail);
        driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
        for (int second = 0;; second++) {
            if (second >= 60) fail("timeout");
            try { if (failText.equals(driver.findElement(By.cssSelector("#reg-view > h2")).getText())) break;
            } catch (Exception e) {}
            Thread.sleep(1000);
        }
        assertEquals(failText, driver.findElement(By.cssSelector("#reg-view > h2")).getText());
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
