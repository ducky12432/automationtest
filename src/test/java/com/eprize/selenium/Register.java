package com.eprize.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import java.security.SecureRandom;
import java.math.BigInteger;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;

/**
 * Created by dnewell on 3/20/16.
 * This Test Class Registers a new user and sends an invitation email to a friend.
 */
public class Register {
    private SecureRandom random = new SecureRandom();
    private WebDriver driver;
    private String baseUrl;
    private String userNameFirst = "Don";
    private String userNameLast = "Newell";
    private String userEmail = "newell.donald+" + randomText() + "@gmail.com";
    private String userAge = "25-35";
    private String userPhone = "3152940003";
    private String friendName = "John";
    private String friendEmail = "ducky12432+" + randomText() +  "@hotmail.com";
    private String registrationComplete = "How nice of you to give your friend a chance to win. " +
            "I hope they appreciate your generosity. Come back tomorrow for another chance to win.";

    /**
     *
     * @return
     */
    public String randomText() {
        return new BigInteger(16, random).toString(16);
    }

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
     * @throws Exception
     */
    @Test
    public void testRegister() throws Exception {
        driver.get(baseUrl + "automationtest/");
        if (driver.findElement(By.cssSelector("h2")).getText().equals("Login Now")){
            driver.findElement(By.linkText("Not registered?")).click();
        }
        driver.findElement(By.id("first_name")).clear();
        driver.findElement(By.id("first_name")).sendKeys(userNameFirst);
        driver.findElement(By.id("last_name")).clear();
        driver.findElement(By.id("last_name")).sendKeys(userNameLast);
        driver.findElement(By.id("email_2")).clear();
        driver.findElement(By.id("email_2")).sendKeys(userEmail);
        new Select(driver.findElement(By.name("age"))).selectByVisibleText(userAge);
        driver.findElement(By.id("primary_opt_in")).click();
        driver.findElement(By.id("mobile_opt_in")).click();
        driver.findElement(By.cssSelector("#mobile_phone_number_div > label.infield-label")).click();
        driver.findElement(By.id("mobile_phone_number")).clear();
        driver.findElement(By.id("mobile_phone_number")).sendKeys(userPhone);
        driver.findElement(By.cssSelector("div.profile > div.submit > button[type=\"submit\"]")).click();
        driver.findElement(By.id("to_name1")).clear();
        driver.findElement(By.id("to_name1")).sendKeys(friendName);
        driver.findElement(By.id("to_email1")).clear();
        driver.findElement(By.id("to_email1")).sendKeys(friendEmail);
        driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
        assertEquals(driver.findElement(By.cssSelector("p")).getText(), registrationComplete);

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
