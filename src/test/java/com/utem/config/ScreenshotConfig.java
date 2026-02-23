package com.utem.config;

import java.time.format.DateTimeFormatter;

/**
 * Configuration class for screenshot capture settings
 * Customize these settings to match your project requirements
 */
public class ScreenshotConfig {

    // Directory where screenshots will be stored
    public static final String SCREENSHOTS_DIRECTORY = "screenshots";

    // Enable/disable automatic screenshot capture on failure
    public static final boolean CAPTURE_ON_FAILURE = true;

    // Enable/disable automatic screenshot file storage
    public static final boolean SAVE_TO_FILE = true;

    // Enable/disable Base64 encoding (required for 256-bit embedding)
    public static final boolean USE_BASE64_ENCODING = true;

    // Maximum width for HTML embedded screenshots (in pixels)
    public static final int DEFAULT_HTML_WIDTH = 800;

    // Screenshot timestamp format
    public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd_HH-mm-ss-SSS";

    // Screenshot file extension
    public static final String SCREENSHOT_EXTENSION = ".png";

    // Enable console logging of screenshot details
    public static final boolean LOG_TO_CONSOLE = true;

    // Enable Base64 preview in console (first N characters)
    public static final int BASE64_PREVIEW_LENGTH = 100;

    /**
     * Get the complete screenshot directory path
     * Can be overridden for custom paths
     */
    public static String getScreenshotDirectory() {
        return SCREENSHOTS_DIRECTORY;
    }

    /**
     * Get the DateTimeFormatter for timestamp
     */
    public static DateTimeFormatter getTimestampFormatter() {
        return DateTimeFormatter.ofPattern(TIMESTAMP_FORMAT);
    }

    /**
     * Get the screenshot filename format
     * @param testName Name of the test
     * @param timestamp Timestamp string
     * @return Formatted filename
     */
    public static String getScreenshotFilename(String testName, String timestamp) {
        return String.format("%s_%s%s", testName, timestamp, SCREENSHOT_EXTENSION);
    }

    /**
     * Print current configuration (for debugging)
     */
    public static void printConfiguration() {
        System.out.println("=== Screenshot Configuration ===");
        System.out.println("Directory: " + getScreenshotDirectory());
        System.out.println("Capture on Failure: " + CAPTURE_ON_FAILURE);
        System.out.println("Save to File: " + SAVE_TO_FILE);
        System.out.println("Base64 Encoding: " + USE_BASE64_ENCODING);
        System.out.println("Default HTML Width: " + DEFAULT_HTML_WIDTH + "px");
        System.out.println("Timestamp Format: " + TIMESTAMP_FORMAT);
        System.out.println("File Extension: " + SCREENSHOT_EXTENSION);
        System.out.println("Log to Console: " + LOG_TO_CONSOLE);
        System.out.println("================================");
    }
}

