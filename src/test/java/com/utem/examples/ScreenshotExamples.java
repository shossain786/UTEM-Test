package com.utem.examples;

import com.utem.base.BaseTest;
import com.utem.utils.ScreenshotUtil;
import com.utem.utils.ScreenshotReporter;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

/**
 * Example usage of screenshot utilities in step definitions
 * These are just examples - uncomment and customize as needed
 */
public class ScreenshotExamples extends BaseTest {

    // Example 1: Capture screenshot on demand in a step
    @When("I take a screenshot after clicking button")
    public void capture_screenshot_on_demand() {
        // Perform action
        // driver.findElement(...).click();

        // Capture screenshot with Base64 encoding
        String base64Screenshot = ScreenshotUtil.captureScreenshotAsBase64(driver, "button_click_result");
        System.out.println("Screenshot captured: " + base64Screenshot.substring(0, 50) + "...");
    }

    // Example 2: Generate HTML-ready embedded screenshot
    @Then("I capture screenshot for report with custom width")
    public void capture_screenshot_for_html_report() {
        // Generate HTML with embedded screenshot (600px width)
        String htmlContent = ScreenshotReporter.getEmbeddedScreenshotHtml(driver, "test_result", 600);
        System.out.println("HTML Content: " + htmlContent);

        // This HTML can be used in:
        // - Cucumber HTML Reports
        // - Custom test reports
        // - Email notifications
        // - Dashboard displays
    }

    // Example 3: Get 256-bit encoded screenshot for secure storage
    @Then("I capture secure screenshot for database storage")
    public void capture_secure_screenshot() {
        String secure256BitScreenshot = ScreenshotUtil.get256BitEncodedScreenshot(driver, "secure_capture");
        // Store in database or encrypted storage
        System.out.println("Secure screenshot (Base64): " + secure256BitScreenshot);
    }

    // Example 4: Load screenshot from file and convert to Base64
    @When("I load existing screenshot file")
    public void load_screenshot_from_file() {
        String base64FromFile = ScreenshotReporter.getBase64FromFile("screenshots/previous_screenshot.png");
        if (base64FromFile != null) {
            System.out.println("Loaded screenshot as Base64: " + base64FromFile.substring(0, 50) + "...");
        }
    }

    // Example 5: Save Base64 screenshot back to file
    @Then("I save Base64 screenshot to file")
    public void save_base64_to_file() {
        String base64Screenshot = ScreenshotUtil.captureScreenshotAsBase64(driver, "temp_screenshot");

        // Convert and save
        boolean success = ScreenshotReporter.writeBase64ToFile(
            base64Screenshot,
            "screenshots/converted_screenshot.png"
        );

        if (success) {
            System.out.println("Successfully saved Base64 screenshot to file");
        }
    }

    // Example 6: Get byte array screenshot (for advanced processing)
    @When("I need raw screenshot bytes")
    public void get_screenshot_bytes() {
        byte[] screenshotBytes = ScreenshotUtil.captureScreenshotAsBytes(driver);

        if (screenshotBytes != null) {
            System.out.println("Screenshot size: " + screenshotBytes.length + " bytes");
            // Process bytes as needed
        }
    }
}

