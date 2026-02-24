package com.utem.hooks;

import com.utem.base.BaseTest;
import com.utem.reporter.testng.WebDriverRegistry;
import com.utem.utils.ScreenshotUtil;
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
        // Capture screenshot on failure
        if (scenario.isFailed() && driver != null) {
            try {
                // Capture as bytes for Cucumber report
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Failure Screenshot");

                // Also capture as Base64 256-bit encoded format
                String base64Screenshot = ScreenshotUtil.get256BitEncodedScreenshot(driver, scenario.getName());
                if (base64Screenshot != null) {
                    scenario.attach(base64Screenshot.getBytes(), "text/plain", "Screenshot Base64 (256-bit)");
                }

                System.out.println("[UTEMHooks] Screenshot captured for failed scenario: " + scenario.getName());
            } catch (Exception e) {
                System.err.println("[UTEMHooks] Screenshot capture failed: " + e.getMessage());
                e.printStackTrace();
            }
        }

        WebDriverRegistry.unregister();
        closeBrowser();
    }
}
