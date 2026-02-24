package com.utem.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class BaseTest {
    public static WebDriver driver;

    public void openSite() {
        driver.get("https://panjatan.netlify.app/");
        waitTime(3);
    }

    public void waitTime(int sec) {
        try {
            Thread.sleep(sec * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void startBrowser() {
        ChromeOptions options = new ChromeOptions();

        // Check if running in CI/CD environment
        String ciEnvironment = System.getenv("CI");
        if (ciEnvironment != null && !ciEnvironment.isEmpty()) {
            // Headless mode for CI/CD pipelines
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
        } else {
            // Regular mode for local development
            options.addArguments("--start-maximized");
        }

        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        if (ciEnvironment == null || ciEnvironment.isEmpty()) {
            driver.manage().window().maximize();
        }
    }

    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
