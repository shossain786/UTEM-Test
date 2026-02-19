package com.utem.pages;

import com.utem.base.BaseTest;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPage extends BaseTest {
    By firstName = By.id("firstName");
    By email = By.id("email");
    By country = By.id("country");
    By registerBtn = By.xpath("//button[contains(text(), 'Register')]");

    public void enterFirstName(String string) {
        driver.findElement(firstName).clear();
        driver.findElement(firstName).sendKeys(string);
    }
    public void enterEmail(String string) {
        driver.findElement(email).clear();
        driver.findElement(email).sendKeys(string);
    }
    public void selectCountry(String string) {
        Select countrySelect = new Select(driver.findElement(country));
        countrySelect.selectByVisibleText(string);
    }
    public void clickRegisterBtn() {
        driver.findElement(registerBtn).click();
    }

    public String getAlertMessage() {
        waitTime(3);
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }
}
