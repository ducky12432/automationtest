package com.eprize.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class Login {
    private SecureRandom random = new SecureRandom();
    private WebDriver driver;
    private String baseUrl;

    //setting the variable in the suite is not the best practise but for this purpose it will work.
    //The best case is to use a generic set of parameters that are sent in as maven parameters when executed in Jenkins
    //
    private String userEmail = "newell.donald@gmail.com";
    private String friendName = "John";
    private String friendEmail = "ducky12432+" + randomText() + "@hotmail.com";
    private String loginMessage = "You've already used all of your plays for now. " +
            "Please come back for another chance to win!";
    private String fillEmail = "Fill out the form below to tell some friends about this promotion. " +
            "They'll receive the email from you.";
    private String userFriend = "How nice of you to give your friends a chance to win. " +
            "I hope they appreciate your generosity. " +
            "Come back tomorrow for another chance to win.";

    public String randomText() {
        return new BigInteger(16, random).toString(16);
    }


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
        driver.findElement(By.id("email")).sendKeys(userEmail);
        driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);\

        //had some page load timing issues. Make sure to catch the correct page element.
        for (int second = 0; ; second++) {
            if (second >= 60) fail("timeout");
            try {
                if (loginMessage.equals(driver.findElement(By.cssSelector("p")).getText())) break;
            } catch (Exception e) {

            }
            Thread.sleep(1000);
        }

        driver.findElement(By.linkText("Continue/Close")).click();
        assertEquals(fillEmail, driver.findElement(By.cssSelector("p")).getText());

        //we have already covered this in registration but it doesnt hurt to make sure the journey though this is also in working order.

        driver.findElement(By.id("to_name1")).clear();
        driver.findElement(By.id("to_name1")).sendKeys(friendName);
        driver.findElement(By.id("to_email1")).clear();
        driver.findElement(By.id("to_email1")).sendKeys(friendEmail);
        driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
        assertEquals(userFriend, driver.findElement(By.cssSelector("p")).getText());


    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
