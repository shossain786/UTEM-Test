package com.utem.hooks;

import com.utem.base.BaseTest;
import com.utem.reporter.junit5.WebDriverRegistry;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class UTEMHooks extends BaseTest {
    @Before
    public void before() {
        startBrowser();
        WebDriverRegistry.register(driver);
        openSite();
    }

    @After
    public void after() {
        WebDriverRegistry.unregister();
        closeBrowser();
    }
}
