package com.utem.pages;

import com.utem.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends BaseTest {
    By username = By.id("username");
    By password = By.id("password");
    By loginBtn = By.xpath("//button[contains(text(), 'Login')]");
    By formsLink = By.linkText("Forms");
    By usernameError = By.id("usernameError");
    By passwordError = By.id("passwordError");

    public void login(String user, String pass) {
        driver.findElement(username).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(loginBtn).click();
    }

    public void clickForms() {
        driver.findElement(formsLink).click();
    }

    public void enterUsername(String user) {
        driver.findElement(username).clear();
        driver.findElement(username).sendKeys(user);
    }

    public void enterPassword(String pass) {
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(pass);
    }

    public void clickLogin() {
        driver.findElement(loginBtn).click();
    }

    public void verifySuccessfulLogin() {
        // Placeholder for assertion
        // Example: Check for success message or redirect
    }

    public void verifyValidationErrors() {
        // Placeholder for assertion
        // Example: Check that error messages are displayed
        try {
            WebElement usernameErrorElement = driver.findElement(usernameError);
            WebElement passwordErrorElement = driver.findElement(passwordError);
        } catch (Exception e) {
            System.out.println("Error elements not found");
        }
    }
}
