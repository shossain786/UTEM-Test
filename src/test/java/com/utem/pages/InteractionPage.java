package com.utem.pages;

import com.utem.base.BaseTest;
import org.openqa.selenium.By;

public class InteractionPage extends BaseTest {
    By actionButton = By.id("actionBtn");
    By loadButton = By.id("loadContent");

    public void clickActionButton() {
        driver.findElement(actionButton).click();
    }

    public void loadDynamicContent() {
        driver.findElement(loadButton).click();
    }
}
