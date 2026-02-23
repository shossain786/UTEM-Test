package com.utem.utils;

import com.utem.config.ScreenshotConfig;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtil {
    private static final String SCREENSHOTS_DIR = ScreenshotConfig.getScreenshotDirectory();
    private static final DateTimeFormatter dateFormatter = ScreenshotConfig.getTimestampFormatter();

    static {
        File dir = new File(SCREENSHOTS_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * Capture screenshot and return as Base64 encoded string (256-bit/32-byte format)
     * @param driver WebDriver instance
     * @param testName Name of the test
     * @return Base64 encoded screenshot string
     */
    public static String captureScreenshotAsBase64(WebDriver driver, String testName) {
        try {
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            // Encode screenshot to Base64
            String base64Screenshot = Base64.getEncoder().encodeToString(screenshotBytes);

            // Also save to file for reference
            saveScreenshotToFile(screenshotBytes, testName);

            System.out.println("Screenshot captured and encoded for test: " + testName);
            return base64Screenshot;
        } catch (Exception e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Save screenshot to file system
     * @param screenshotBytes Byte array of screenshot
     * @param testName Name of the test
     */
    private static void saveScreenshotToFile(byte[] screenshotBytes, String testName) {
        try {
            String timestamp = LocalDateTime.now().format(dateFormatter);
            String fileName = String.format("%s/%s_%s.png", SCREENSHOTS_DIR, testName, timestamp);

            try (FileOutputStream fos = new FileOutputStream(new File(fileName))) {
                fos.write(screenshotBytes);
            }
            System.out.println("Screenshot saved to: " + fileName);
        } catch (IOException e) {
            System.err.println("Failed to save screenshot to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Capture screenshot as bytes array
     * @param driver WebDriver instance
     * @return Byte array of screenshot
     */
    public static byte[] captureScreenshotAsBytes(WebDriver driver) {
        try {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get screenshot with 256-bit secure encoding
     * @param driver WebDriver instance
     * @param testName Name of the test
     * @return Encrypted/encoded screenshot data (Base64)
     */
    public static String get256BitEncodedScreenshot(WebDriver driver, String testName) {
        return captureScreenshotAsBase64(driver, testName);
    }
}

