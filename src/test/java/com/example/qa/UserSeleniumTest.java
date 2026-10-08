package com.example.qa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class UserSeleniumTest {

    @Test
    void testAddUser() {

        WebDriver driver = new ChromeDriver();

        try {
            driver.get("http://localhost:8080/");

            driver.findElement(By.id("name"))
                  .sendKeys("Selenium User");

            driver.findElement(By.id("email"))
                  .sendKeys("selenium" + System.currentTimeMillis() + "@gmail.com");

            driver.findElement(By.id("addUser"))
                  .click();

            String message =
                    driver.findElement(By.id("message"))
                          .getText();

            System.out.println("Actual message: " + message);

            assertEquals("User created successfully", message);

        } finally {
            driver.quit();
        }
    }
}