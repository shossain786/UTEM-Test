package com.utem.hooks;

import com.utem.base.BaseTest;
import com.utem.reporter.testng.WebDriverRegistry;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class UTEMHooks extends BaseTest {
    @Before
    public void before() {
        startBrowser();
        WebDriverRegistry.register(driver);
        openSite();
    }

    @After
    public void after(Scenario scenario) {
        // Attach screenshot to Cucumber HTML report on failure
        if (scenario.isFailed() && driver != null) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Failure Screenshot");
            } catch (Exception e) {
                System.err.println("[UTEMHooks] Screenshot capture failed: " + e.getMessage());
            }
        }

        WebDriverRegistry.unregister();
        closeBrowser();
    }
}
