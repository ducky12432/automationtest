package com.eprize.selenium;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertTrue;

public class RegisterUser {


    @Test
    public void Register() {
        WebDriver driver = new FirefoxDriver();
        driver.get("https://helloworld.promo.eprize.com/automationtest/");

        assertTrue(driver.getTitle().startsWith("Sample Solution"));
        driver.close();

    }
}
