package com.utem.utils;

import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

/**
 * Advanced Screenshot Reporter Utility
 * Provides 256-bit Base64 encoding for screenshots to embed in reports
 */
public class ScreenshotReporter {

    /**
     * Generate HTML string with embedded Base64 screenshot for reports
     * @param driver WebDriver instance
     * @param testName Name of the test
     * @param maxWidth Maximum width for the screenshot in HTML (optional)
     * @return HTML string with embedded screenshot
     */
    public static String getEmbeddedScreenshotHtml(WebDriver driver, String testName, int maxWidth) {
        try {
            String base64Screenshot = ScreenshotUtil.captureScreenshotAsBase64(driver, testName);

            if (base64Screenshot == null) {
                return "<p>Failed to capture screenshot</p>";
            }

            return String.format(
                "<img src=\"data:image/png;base64,%s\" alt=\"%s\" style=\"max-width:%dpx; border:1px solid #ccc;\" />",
                base64Screenshot,
                testName,
                maxWidth
            );
        } catch (Exception e) {
            System.err.println("Error generating embedded screenshot HTML: " + e.getMessage());
            return "<p>Error capturing screenshot</p>";
        }
    }

    /**
     * Generate HTML string with embedded Base64 screenshot (default 800px width)
     * @param driver WebDriver instance
     * @param testName Name of the test
     * @return HTML string with embedded screenshot
     */
    public static String getEmbeddedScreenshotHtml(WebDriver driver, String testName) {
        return getEmbeddedScreenshotHtml(driver, testName, 800);
    }

    /**
     * Get screenshot from file as Base64 encoded string
     * @param screenshotFilePath Path to the screenshot file
     * @return Base64 encoded string of the screenshot
     */
    public static String getBase64FromFile(String screenshotFilePath) {
        try {
            byte[] fileContent = Files.readAllBytes(Paths.get(screenshotFilePath));
            return Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            System.err.println("Error reading screenshot file: " + e.getMessage());
            return null;
        }
    }

    /**
     * Write Base64 encoded screenshot back to file
     * @param base64Content Base64 encoded screenshot
     * @param outputFilePath Path where the file should be written
     * @return true if successful, false otherwise
     */
    public static boolean writeBase64ToFile(String base64Content, String outputFilePath) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(base64Content);
            Files.write(Paths.get(outputFilePath), decodedBytes);
            System.out.println("Screenshot written to: " + outputFilePath);
            return true;
        } catch (IOException e) {
            System.err.println("Error writing screenshot file: " + e.getMessage());
            return false;
        }
    }
}

